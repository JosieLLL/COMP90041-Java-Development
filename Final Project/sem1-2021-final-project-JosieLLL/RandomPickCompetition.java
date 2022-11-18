/*
 * Student name: Jiayi Li
 * Student ID: 1097419
 * LMS username: jiayi9
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class RandomPickCompetition extends Competition {

    private final int FIRST_PRIZE = 50000;
    private final int SECOND_PRIZE = 5000;
    private final int THIRD_PRIZE = 1000;
    private final int[] prizes = {FIRST_PRIZE, SECOND_PRIZE, THIRD_PRIZE};
    private final int MAX_WINNING_ENTRIES = 3;

    /**
     * Default Constructor
     */
    public RandomPickCompetition(){
        super();
    }



    /**
     * Constructor with more RandomPick competition information
     * @param keyboard      for reading the user input
     * @param id            competition ID
     * @param testingMode   true - if the competition is under testing mode
     */
    public RandomPickCompetition (Scanner keyboard, int id, boolean testingMode){
        super(keyboard, id, testingMode);
    }



    /**
     * Overwrite addEntries method derived from its parent class
     * No numbers will be created for each bill entry
     * @param keyboard  for reading the user input
     * @param dataset   for providing bill information(ID, Member ID, Amount, If has been used)
     */
    @Override
    public void addEntries(Scanner keyboard, DataProvider dataset) {
        int totalNumOfEntries;

        OuterLoop:
        while (true){

            Bill bill = AskBillID(keyboard, dataset);
            totalNumOfEntries = (int) bill.getTotalAmount() / getEligiblity();

            System.out.printf("This bill ($%s) is eligible for %d entries.\n",
                    String.valueOf(bill.getTotalAmount()), totalNumOfEntries);

            // Temporary holding entries for this bill
            ArrayList<Entry> entries = new ArrayList<Entry>();

            // Creating entry numbers
            for (int i = 0; i < totalNumOfEntries; i++){
                setTotalEntries(getTotalEntries() + 1);
                Entry entry = new Entry(getTotalEntries(),
                        String.valueOf(bill.getId()), String.valueOf(bill.getMemberId()));
                entries.add(entry);
            }

            // Print out all entries for this bill
            System.out.println("The following entries have been automatically generated:");
            for (int i = 0; i < entries.size(); i++) {
                entries.get(i).printEntryID();
            }

            // Put entries of this bill into the whole entry pool of this competition
            setEntries(entries);

            // Flag this bill has been used
            bill.setIfUsed(true);

            // Ask the user if s/he would like to add more entry
            InnerLoop_MoreEntries:
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

            ArrayList<Entry> entries = getEntries();
            ArrayList<Entry> winningEntries = new ArrayList<Entry>();
            int totalPrize = 0;

            Random randomGenerator = null;
            if (this.getIsTestingMode() == true) {
                randomGenerator = new Random(this.getId());
            } else {
                randomGenerator = new Random();
            }

            // Drawing the three winners
            int winningEntryCount = 0;
            while (winningEntryCount < MAX_WINNING_ENTRIES) {
                int winningEntryIndex = randomGenerator.nextInt(entries.size());

                Entry winningEntry = entries.get(winningEntryIndex);

                /**
                 * Ensure that once an entry has been selected,
                 * it will not be selected again.
                 * And one customer gets at most one winning entry.
                 */
                if (winningEntry.getPrize() == 0 && winningEntry.getIfWon() == false) {
                    int currentPrize = prizes[winningEntryCount];
                    winningEntry.setPrize(currentPrize);
                    String winningCustomer = winningEntry.getMemberId();
                    for (int i = 0; i < entries.size(); i++){
                        if (entries.get(i).getMemberId().equals(winningCustomer)){
                            entries.get(i).setIfWon(true);
                        }
                    }
                    winningEntries.add(winningEntry);
                }

                winningEntryCount++;
            }

            // Set the number of total winners for this competition
            setTotalWinner(winningEntries.size());

            // Set the total prize for this competition
            for (int i = 0; i < winningEntries.size(); i++){
                totalPrize += winningEntries.get(i).getPrize();
            }
            setTotalPrize(totalPrize);

            // Print out all winning entries
            Collections.sort(winningEntries, Entry::compareTo);
            System.out.println(toString());
            System.out.println("Winning entries:");
            for (int i = 0; i < winningEntries.size(); i++){

                Member winnerInfo = dataProvider.getMember(
                        Integer.parseInt(winningEntries.get(i).getMemberId()));
                String winnerName = winnerInfo.getName();

                System.out.printf("Member ID: %s, Member Name: %s, Entry ID: %d, Prize: %-5d\n",
                        winningEntries.get(i).getMemberId(), winnerName,
                        winningEntries.get(i).getEntryId(), winningEntries.get(i).getPrize());
            }

            // After drawing winners, competition deactivated
            setActive(false);
        }

    }



    /**
     * Printer of this competition
     * @return  competition ID, Name, Type
     */
    @Override
    public String toString(){
        return "Competition ID: " + getId() + ", Competition Name: " + getName() +
                ", Type: RandomPickCompetition";
    }

}
