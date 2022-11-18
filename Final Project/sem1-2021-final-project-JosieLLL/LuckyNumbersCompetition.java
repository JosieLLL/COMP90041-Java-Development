/*
 * Student name: Jiayi Li
 * Student ID: 1097419
 * LMS username: jiayi9
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class LuckyNumbersCompetition extends Competition {

    private final int ENTRY_SIZE = 7;

    /**
     * Default Constructor
     */
    public LuckyNumbersCompetition(){
        super();
    }



    /**
     * Constructor with more LuckyNumber competition information
     * @param keyboard      for reading the user input
     * @param id            competition ID
     * @param testingMode   true - if the competition is under testing mode
     */
    public LuckyNumbersCompetition (Scanner keyboard, int id, boolean testingMode){
        super(keyboard, id, testingMode);
    }



    /**
     * Overwrite addEntries method derived from its parent class
     * Total number of entries which can be created for a bill will be based on the bill amount
     * The number of manual entries will be chosen by the user
     * The rest entries will be automatically generated
     * @param keyboard  for reading the user input
     * @param dataset   for providing bill information(ID, Member ID, Amount, If has been used)
     */
    @Override
    public void addEntries(Scanner keyboard, DataProvider dataset) {
        int totalNumOfEntries;
        int manualEntries;

        OuterLoop:
        while (true) {

            Bill bill = AskBillID(keyboard, dataset);
            totalNumOfEntries = (int) bill.getTotalAmount() / getEligiblity();

            System.out.printf("This bill ($%s) is eligible for %d entries. " +
                            "How many manual entries did the customer fill up?: \n",
                    String.valueOf(bill.getTotalAmount()), totalNumOfEntries);


            // Ask user to input how many manual entries s/he would like to create
            InnerLoop_AskNumOfManualEntries:
            while (true){
                String manualEntry = keyboard.nextLine();

                try{
                    manualEntries = Integer.parseInt(manualEntry);

                    // Check whether the number of manual entry is in correct scope
                    if (manualEntries < 0 || manualEntries > totalNumOfEntries){
                        System.out.println("The number must be in the range from 0 to 6. " +
                                "Please try again.");
                    } else {
                        break InnerLoop_AskNumOfManualEntries;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The number must be in the range from 0 to 6. " +
                            "Please try again.");
                }

            }

            // Temporary holding entries for this bill
            ArrayList<Entry> entries = new ArrayList<Entry>();

            // Creating entry numbers
            // If the number of manual entry is 0, creating all AutoEntries
            if (manualEntries == 0){
                for (int i = 0; i < totalNumOfEntries; i++){
                    createAutoEntries(getIsTestingMode(), String.valueOf(bill.getId()),
                            bill, entries);
                }

            // If the number of manual entry is not 0, creating ManualEntries + AutoEntries
            } else {
                for (int i = 0; i < manualEntries; i++){
                    setTotalEntries(getTotalEntries() + 1);
                    NumbersEntry entry = new NumbersEntry(getTotalEntries(),
                            String.valueOf(bill.getId()), String.valueOf(bill.getMemberId()));
                    entry.setNumbers(entry.createNumbers(keyboard));
                    entries.add(entry);
                }

                for (int j = 0; j < (totalNumOfEntries - manualEntries); j++){
                    createAutoEntries(getIsTestingMode(), String.valueOf(bill.getId()), bill,
                            entries);
                }

            }

            // Print out all entries for this bill
            System.out.println("The following entries have been added:");
            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i) instanceof AutoNumbersEntry) {
                    ((AutoNumbersEntry) entries.get(i)).printEntries();
                } else if (entries.get(i) instanceof NumbersEntry) {
                    ((NumbersEntry) entries.get(i)).printEntries();
                }
            }

            // Put entries of this bill into the whole entry pool of this competition
            setEntries(entries);

            // Flag this bill has been used
            bill.setIfUsed(true);


            // Ask the user if s/he would like to add more entry
            InnerLoop_AskMoreEntries:
            while (true) {
                System.out.println("Add more entries (Y/N)?");
                String yesOrNo = keyboard.nextLine();
                if (yesOrNo.length() == getUserOptionLength()){
                    switch (yesOrNo.charAt(0)){
                        case 'Y':
                        case 'y':
                            continue OuterLoop;

                        case 'N':
                        case 'n':
                            break OuterLoop;

                        default:
                            System.out.println("Unsupported option. Please try again!");
                    }
                } else {
                    System.out.println("Unsupported option. Please try again!");
                }
            }


        }


    }



    /**
     * Helper for addEntries method - automatically generating entry numbers
     * @param mode      true - testing mode, false - normal mode
     * @param billId    Bill ID
     * @param bill      The Bill
     * @param entries   Entries of this bill
     */
    private void createAutoEntries(boolean mode, String billId, Bill bill,
                                   ArrayList<Entry> entries){

        if (mode == true) { // testing mode - seed required
            setTotalEntries(getTotalEntries() + 1);
            AutoNumbersEntry autoEntry = new AutoNumbersEntry(getTotalEntries(),
                    billId, String.valueOf(bill.getMemberId()));
            // Using the number of entries in the currently active competition as seed
            autoEntry.setNumbers(autoEntry.createNumbers(getTotalEntries() - 1));
            entries.add(autoEntry);

        } else { // normal mode - no seed
            setTotalEntries(getTotalEntries() + 1);
            AutoNumbersEntry autoEntry = new AutoNumbersEntry(getTotalEntries(),
                    billId, String.valueOf(bill.getMemberId()));
            autoEntry.setNumbers(autoEntry.createNumbers());
            entries.add(autoEntry);
        }

    }



    /**
     * Overwrite drawWinners method derived from its parent class
     * @param keyboard      for reading the user input
     * @param dataProvider  for providing member information(ID, Name)
     */
    @Override
    public void drawWinners(Scanner keyboard, DataProvider dataProvider) {

        // Check if there is a competition created
        if (getTotalEntries() == 0){
            System.out.println("The current competition has no entries yet!");
        }
        else {
            ArrayList<Entry> winningMembers = new ArrayList<Entry>();
            ArrayList<Entry> finalWinners = new ArrayList<Entry>();
            Entry highestPrizeEntry = null;
            int matchNums;
            int prize;
            int totalPrize = 0;

            // Creating lucky entry
            AutoNumbersEntry luckyEntry = new AutoNumbersEntry(getTotalEntries() + 1);

            // Check the competition mode
            if (getIsTestingMode() == false) { // No seed required
                luckyEntry.setNumbers(luckyEntry.createNumbers());
            }
            else { // Seed required - Competition ID
                luckyEntry.setNumbers(luckyEntry.createNumbers(getId()));
            }

            // Get numbers of LuckyEntry for comparison
            int[] luckyNumbers = luckyEntry.getNumbers();

            // Get numbers of each entry in the entry pool which is already ordered by the
            // incremented totalEntries as entryId
            int[] nums = new int[ENTRY_SIZE];

            // Print the luckyEntry out
            System.out.println(toString());
            System.out.print("Lucky Numbers:");
            luckyEntry.printNumbers();

            // Compare luckyEntry with each entry in the entry pool, and find the prize
            for (int i = 0; i < getEntries().size(); i++){
                matchNums = 0;
                prize = 0;

                if (getEntries().get(i) instanceof AutoNumbersEntry){
                    nums = ((AutoNumbersEntry) getEntries().get(i)).getNumbers();
                } else {
                    nums = ((NumbersEntry) getEntries().get(i)).getNumbers();
                }

                for (int j = 0; j < luckyNumbers.length; j++){
                    for (int k = 0; k < nums.length; k++){
                        if (luckyNumbers[j] == nums[k]){
                            matchNums++;
                        }
                    }
                }

                prize = winningPolicy(matchNums);

                getEntries().get(i).setPrize(prize);
            }

            // Check if there is a member winning multiple entries
            // Award him/her the highest prize with the first winning entry

            // Get all members with a prize from entry pool
            for (int i = 0; i < getEntries().size(); i++){
                if (getEntries().get(i).getPrize() != 0){
                    winningMembers.add(getEntries().get(i));
                }
            }

            // Sort winning members by prize in descending order to find the highest one
            Comparator<Entry> compareByPrize = new Comparator<Entry>(){
                @Override
                public int compare(Entry o1, Entry o2) {

                    if (o1.getPrize() > o2.getPrize()){
                        return 1;
                    } else if (o1.getPrize() < o2.getPrize()){
                        return  -1;
                    } else {
                        return 0;
                    }

                }
            };
            Collections.sort(winningMembers, Collections.reverseOrder(compareByPrize));


            // If a member wins several entries - keep the first one (highest-prize one),
            // remove the rest of winning entries by setting the prize to 0
            for (int i = 0; i < winningMembers.size() - 1; i++) {
                for (int j = i + 1; j < winningMembers.size(); j++) {
                    if (winningMembers.get(i).getMemberId()
                            .equals(winningMembers.get(j).getMemberId())) {
                        winningMembers.get(j).setPrize(0);
                    }
                }
            }

            // Get the final winners from winning members
            for (int i = 0; i < winningMembers.size(); i++) {
                if (winningMembers.get(i).getPrize() != 0){
                    finalWinners.add(winningMembers.get(i));
                }
            }

            // Set the number of total winners for this competition
            setTotalWinner(finalWinners.size());

            // Set the total prize for this competition
            for (int i = 0; i < finalWinners.size(); i++){
                totalPrize += finalWinners.get(i).getPrize();
            }
            setTotalPrize(totalPrize);

            // Print out all the final winners
            Collections.sort(finalWinners, Entry::compareTo);
            System.out.println("Winning entries:");
            for (int i = 0; i < finalWinners.size(); i++){

                Member winnerInfo = dataProvider.getMember(
                        Integer.parseInt(finalWinners.get(i).getMemberId()));
                String winnerName = winnerInfo.getName();

                System.out.printf("Member ID: %s, Member Name: %s, Prize: %-5d\n",
                        finalWinners.get(i).getMemberId(),
                        winnerName , finalWinners.get(i).getPrize());
                System.out.print("--> ");
                System.out.printf("Entry ID: %d, Numbers:", finalWinners.get(i).getEntryId());
                if (finalWinners.get(i) instanceof AutoNumbersEntry){
                    ((AutoNumbersEntry)finalWinners.get(i)).printNumbers();
                } else if (finalWinners.get(i) instanceof NumbersEntry){
                    ((NumbersEntry)finalWinners.get(i)).printNumbers();
                }

            }

            // After drawing winners, competition deactivated
            setActive(false);
        }

    }



    /**
     * Helper for drawWinners - Winning policy
     * @param matchNumber   how many numbers are matched between LuckyEntry and each bill entry
     * @return              prize for each bill entry
     */
    private int winningPolicy(int matchNumber) {
        int prize = 0;

        switch(matchNumber) {
            case 7:
                prize = 50000;
                break;

            case 6:
                prize = 5000;
                break;

            case 5:
                prize = 1000;
                break;

            case 4:
                prize = 500;
                break;

            case 3:
                prize = 100;
                break;

            case 2:
                prize = 50;
                break;

            default:
                break;
        }
        return prize;
    }



    /**
     * Printer of this competition
     * @return  competition ID, Name, Type
     */
    @Override
    public String toString(){
        return "Competition ID: " + getId() + ", Competition Name: " + getName() +
                ", Type: LuckyNumbersCompetition";
    }

}
