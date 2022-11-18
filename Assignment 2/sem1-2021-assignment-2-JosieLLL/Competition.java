/**
 * @author: Jiayi Li
 * @studentID: 1097419
 * @username(email): jiayi9@student.unimelb.edu.au
 * @Git: https://github.com/COMP90041/sem1-2021-assignment-2-JosieLLL.git
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Competition {

    private String name; //competition name
    private int id; //competition identifier
    private boolean ifActive; //check if there is a competition active

    private int memberId;
    private int billId;
    private float billAmount;
    private int numOfEntriesEachBill; // calculated by billAmount
    private int totalEntriesEachCompetition; // incremented as entries are added - entryId

    private int totalPrize; // total prize points awarded
    private int totalWinner; // total number of winners

    private ArrayList<Integer> allMembers = new ArrayList<Integer>(); // List of MemberID
    private ArrayList<ArrayList<Entry>> entriesPerComptition =
            new ArrayList<ArrayList<Entry>>(); // total entries grouped by MemberID

    private final int DIGIT = 6; // the length of IDs
    private final int THRESHOLD = 50; // eligibility for getting entry
    private final char NORMAL = 'N'; // if the competition is of normal mode
    private final char TEST = 'T'; // if the competition is of testing mode


    // Constructors
    public Competition() {
    }

    public Competition(Scanner keyboard, int id) {
        this.id = id;
        setCompetition(keyboard);
    }


    // Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean getIfActive() {
        return ifActive;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getBillId() {
        return billId;
    }

    public float getBillAmount() {
        return billAmount;
    }

    public int getNumOfEntriesEachBill() {
        return numOfEntriesEachBill;
    }

    public int getTotalEntriesEachCompetition() {
        return totalEntriesEachCompetition;
    }

    public int getTotalPrize() {
        return totalPrize;
    }

    private int getTotalWinner() {
        return totalWinner;
    }

    public ArrayList<Integer> getAllMembers() {
        return allMembers;
    }

    public ArrayList<ArrayList<Entry>> getEntriesPerCompetition() {
        return entriesPerComptition;
    }


    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIfActive(boolean ifActive) {
        this.ifActive = ifActive;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public void setBillAmount(float billAmount) {
        this.billAmount = billAmount;
    }

    public void setNumOfEntriesEachBill(int numOfEntries) {
        this.numOfEntriesEachBill = numOfEntries;
    }

    public void setTotalEntriesEachCompetition(int totalEntries) {
        this.totalEntriesEachCompetition = totalEntries;
    }

    public void setTotalPrize(int prize) {
        totalPrize = totalPrize + prize;
    }

    private void setTotalWinner(int totalWinner) {
        this.totalWinner = totalWinner;
    }

    public void setAllMembers(int memberId) {
        Integer memberID = Integer.valueOf(memberId);
        allMembers.add(memberID);
    }

    public void setEntriesPerCompetition(ArrayList<Entry> entries) {
        entriesPerComptition.add(entries);
    }


    // Ask user to input competition information
    public void setCompetition(Scanner keyboard) {
        System.out.println("Competition name: ");
        keyboard.nextLine(); // skip the white space after menu choice 1
        setName(keyboard.nextLine());
        System.out.println("A new competition has been created!");
        System.out.printf("Competition ID: %d, Competition Name: %s\n", getId(), getName());
        setIfActive(true);
    }


    // Ask user to input entries if eligible
    public void addEntries(Scanner keyboard, char mode) {

        OuterLoop:
        while (true) {
            setMemberId(askCustomer(keyboard, "Member ID"));
            setBillId(askCustomer(keyboard, "Bill ID"));
            setBillAmount(askBillAmount(keyboard, "Total amount: "));

            // If a user is eligible to get an entry, create entries for this user
            if (getBillAmount() > THRESHOLD){
                setAllMembers(getMemberId());
                setNumOfEntriesEachBill((int) Math.floor(getBillAmount() / THRESHOLD));
                createEntries(keyboard, mode);
            }

            // Ask user if wanna add more entries
            InnerLoop:
            while (true) {
                System.out.println("Add more entries (Y/N)?");
                char yesOrNo = keyboard.next().toUpperCase().charAt(0);
                switch (yesOrNo) {
                    case 'Y':
                        continue OuterLoop;
                    case 'N':
                        break OuterLoop;
                    default:
                        System.out.println("Unsupported option. Please try again!");

                }
            }
        }

    }


    // Helper for addEntries(): Ask user's ID info
    private int askCustomer(Scanner keyboard, String askId) {
        while (true) {
            try {
                System.out.println(askId + ": ");
                int idNum = keyboard.nextInt();
                if (String.valueOf(idNum).length() != DIGIT) { // Handling longer/shorter ID
                    System.out.printf("Invalid %s! It must be a 6-digit number. Please try again.\n", askId.toLowerCase());
                }
                else {
                    return idNum;
                }
            }
            catch (Exception e) {
                System.out.printf("Invalid %s! It must be a 6-digit number. Please try again.\n", askId.toLowerCase());
                keyboard.nextLine(); // Skip the mismatched input such as 123abc
            }
        }
    }


    // Helper for addEntries(): Ask user's bill amount
    private float askBillAmount(Scanner keyboard, String askBillAmount) {
        float billAmount;
        System.out.println(askBillAmount);
        billAmount = keyboard.nextFloat();

        // Check if the user is eligible to get an entry
        if (billAmount < THRESHOLD){
            System.out.println("This bill is not eligible for an entry. The total amount is smaller than $50.0");
            return billAmount;
        }
        else {
            return billAmount;
        }
    }


    // Helper for addEntries(): Create numbers of entry
    private void createEntries(Scanner keyboard, char mode) {

        int manualEntries; // Number of manual entries to be created

        while (true){

            // Ask how many manual entries the user wants to get
            System.out.printf("This bill is eligible for %d entries. " +
                    "How many manual entries did the customer fill up?: \n", getNumOfEntriesEachBill());
            manualEntries = keyboard.nextInt();

            // Check if the user input for numbers of manual entry is legal
            if (manualEntries < 0 || manualEntries > getNumOfEntriesEachBill()){
                System.out.println("Unsupported option. Please try again!");
            }
            else {
                break;
            }
        }

        keyboard.nextLine(); // skip the white space after user choice of manual entries

        // Temporarily holding entries for each user(Member ID)
        ArrayList<Entry> entries = new ArrayList<Entry>();

        if (manualEntries == 0) { // All AutoEntries
            for(int i = 0; i < getNumOfEntriesEachBill(); i++) {
                randomEntries(entries, mode);
            }
        }
        else { // ManualEntries + AutoEntries
            for (int i = 0; i < manualEntries; i++) {
                // create manual entries
                totalEntriesEachCompetition++;
                Entry entry = new Entry(keyboard,  totalEntriesEachCompetition);
                entries.add(entry);
            }
            for (int j = 0; j < (getNumOfEntriesEachBill() - manualEntries); j++) {
                randomEntries(entries, mode);
            }
        }

        // Print entries for each user(Member ID)
        System.out.println("The following entries have been added:");
        for (int k = 0; k < entries.size(); k++) {
            entries.get(k).printEntries();
        }

        // Put all entries for a specific user into the total entry list
        setEntriesPerCompetition(entries);
    }


    // Helper for createEntries(): Creating random numbers
    private void randomEntries(ArrayList<Entry> entries, char mode) {
        if (mode == NORMAL) {
            // No seed
            totalEntriesEachCompetition++;
            AutoEntry autoEntry = new AutoEntry(totalEntriesEachCompetition);
            autoEntry.setNumbers(autoEntry.createNumbers());
            entries.add(autoEntry);
        }
        else {
            totalEntriesEachCompetition++;
            AutoEntry autoEntry = new AutoEntry(totalEntriesEachCompetition);
            // Previous entryId as seed
            autoEntry.setNumbers(autoEntry.createNumbers(totalEntriesEachCompetition - 1));
            entries.add(autoEntry);
        }
    }


    // Drawing winners
    public void drawWinners(Scanner keyboard, char mode) {

        // Check if there is a competition created
        if (totalEntriesEachCompetition == 0){
            System.out.println("The current competition has no entries yet!");
        }
        else {
            Entry winner = new Entry(); // Holding winner entry for each user(Member ID)
            int winnerMember = 0; // The user(Member ID) who wins
            int matchNums = 0;
            int prize = 0;
            int totalWinners = 0; // Count the total number of winners


            // Creating lucky entry
            AutoEntry luckyEntry = new AutoEntry(totalEntriesEachCompetition + 1);
            if (mode == NORMAL) { // No seed
                luckyEntry.setNumbers(luckyEntry.createNumbers());
            }
            else { // Seed required - Competition ID
                luckyEntry.setNumbers(luckyEntry.createNumbers(getId()));
            }


            // Get numbers of lucky entry for comparison
            int[] luckyNumbers = luckyEntry.getNumbers();

            // Get numbers of entries from the total entry list which is already ordered by the
            // incremented totalEntriesPerCompetition as entryId
            int[] nums = new int[7];


            // Printer lucky entry out
            System.out.printf("Lucky entry for Competition ID: %d, Competition Name: %s\n", getId(), getName());
            System.out.print("Numbers:");
            luckyEntry.printNumbers();
            System.out.println("Winning entries:");


            // Compare lucky entry with each entry for each user(Member ID)
            // The index i represents how many users(Member ID) in the competition
            for (int i = 0; i < getEntriesPerCompetition().size(); i++) {

                int maxPrize = 0;
                // The index j represents each entry for the user(Member ID)
                for (int j = 0; j < getEntriesPerCompetition().get(i).size(); j++) {

                    nums = getEntriesPerCompetition().get(i).get(j).getNumbers();
                    matchNums = 0;
                    prize = 0;

                    // Compare each number in the lucky entry with those in the user entry
                    for (int k = 0; k < luckyEntry.getSize(); k++) {
                        for (int m = 0; m < nums.length; m++) {
                            if (luckyNumbers[k] == nums[m]) {
                                matchNums++;
                            }
                        }
                    }

                    // Get the prize for each entry
                    prize = winningPolicy(matchNums);

                    // Check if the prize is the highest prize for this user(Member ID)
                    // Entries are already sorted
                    if (prize > maxPrize) {
                        maxPrize = prize;
                        winner = getEntriesPerCompetition().get(i).get(j);
                        winnerMember = getAllMembers().get(i).intValue();
                    }
                }


                // Calculate the total prize after comparison
                setTotalPrize(maxPrize);


                // Only print the winners and the user
                if (winnerMember != 0) { // if the user has winner entry, winnerMember will be
                    // updated by Member ID
                    totalWinners++;
                    setTotalWinner(totalWinners);
                    System.out.printf("Member ID: %-6d, Entry ID: %-6d, Prize: %-5d, ", winnerMember,
                            winner.getEntryId(), maxPrize);
                    System.out.print("Numbers:");
                    winner.printNumbers();
                }
            }

            // After drawing winners, competition ends
            setIfActive(false);
        }

    }


    // Helper for drawWinners(): Winning policy
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


    // Print the report for each competition
    public void report() {

        // Check if the competition is active
        String yesOrNo = "";
        if (getIfActive() == true) {
            yesOrNo = "yes";
        }
        else {
            yesOrNo = "no";
        }

        // Printer
        System.out.printf("\nCompetition ID: %d, name: %s, active: %s\n", getId(), getName(),
                yesOrNo);
        System.out.printf("Number of entries: %d\n", getTotalEntriesEachCompetition());
        if (getIfActive()== false) {
            System.out.println("Number of winning entries: " + getTotalWinner());
            System.out.println("Total awarded prizes: " + getTotalPrize());
        }
    }






}
