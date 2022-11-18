/*
 * Student name: Jiayi Li
 * Student ID: 1097419
 * LMS username: jiayi9
 */

import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

public abstract class Competition implements Serializable {

    private final int USER_OPTION_LENGTH = 1;
    private final int ELIGIBILITY = 50;
    private final int ID_FORMAT = 6;

    private String name; //competition name
    private int id; //competition identifier
    private boolean active; //check if the competition is active
    private boolean testingMode; // check if the competition is under testing mode

    private int totalEntries; // total number of entries created
    ArrayList<Entry> Entries = new ArrayList<Entry>(); // all entries for a competition
    private int totalWinner; // total number of winners drawn
    private int totalPrize; // total prize awarded

    /**
     * Default constructor of a competition
     */
    public Competition(){};



    /**
     * Constructor of a competition with multiple information
     * @param keyboard      for reading the user input
     * @param id            competition ID
     * @param testingMode   whether the competition is of testing mode
     */
    public Competition(Scanner keyboard, int id, boolean testingMode){
        setCompetition(keyboard);
        this.id = id;
        this.testingMode = testingMode;
    }



    /**
     * Setter - set competition name
     * @param name  competition name
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * Setter - set competition ID
     * @param id    competition ID
     */
    public void setId(int id) {
        this.id = id;
    }



    /**
     * Setter - set whether a competition is active
     * @param active    if active - true, otherwise - false
     */
    public void setActive(boolean active) {
        this.active = active;
    }



    /**
     * Setter - set whether a competition is under testing mode
     * @param mode  if is testing mode - true, otherwise - false
     */
    public void setIsTestingMode(boolean mode) { testingMode = mode; }



    /**
     * Setter - set total number of entries created for a competition
     * @param totalEntries  total number of entries
     */
    public void setTotalEntries(int totalEntries) { this.totalEntries = totalEntries; }



    /**
     * Setter - put all entries created together to form an entry pool
     * @param entry     entry created for a bill
     */
    public void setEntries(ArrayList<Entry> entry) { Entries.addAll(entry); }



    /**
     * Setter - set number of total winners for a competition
     * @param totalWinner   number of total winners
     */
    public void setTotalWinner(int totalWinner) { this.totalWinner = totalWinner; }



    /**
     * Setter - set total prize for a competition
     * @param totalPrize    total prize awarded
     */
    public void setTotalPrize(int totalPrize) { this.totalPrize = totalPrize; }



    /**
     * Getter - get competition name
     * @return  competition name
     */
    public String getName() {
        return name;
    }



    /**
     * Getter - get competition ID
     * @return  competition ID
     */
    public int getId() {
        return id;
    }



    /**
     * Getter - get whether a competition is active
     * @return  true - if active, false - if not active
     */
    public boolean getActive() {
        return active;
    }



    /**
     * Getter - get whether a competition is in testing mode
     * @return  true - is testing mode, false - is not testing mode
     */
    public boolean getIsTestingMode(){ return testingMode; }



    /**
     * Getter - get the eligibility for getting an entry
     * @return  50 - at least $50 to get an entry
     */
    public int getEligiblity(){ return ELIGIBILITY; }



    /**
     * Getter - get the length of a user option
     * @return  1 - correct input length when every time a user makes a choice from given options
     */
    public int getUserOptionLength() { return USER_OPTION_LENGTH; }



    /**
     * Getter - get the correct format of bill ID and member ID
     * @return  6 - bill and member ID must be a 6-digit number
     */
    public int getIdFormat(){ return ID_FORMAT; }



    /**
     * Getter - get number of total entries created for a competition
     * @return  number of total entries
     */
    public int getTotalEntries(){ return totalEntries; }



    /**
     * Getter - get all entries in a competition
     * @return  all entries created
     */
    public ArrayList<Entry> getEntries(){ return Entries; }



    /**
     * Getter - get number of total winners for a competition
     * @return  number of total winner
     */
    public int getTotalWinner() { return totalWinner; }



    /**
     * Getter - get total prize awarded for a competition
     * @return  total prize awarded
     */
    public int getTotalPrize() { return totalPrize; }



    /**
     * Ask user to input competition information
     * @param keyboard  for reading the user input
     */
    public void setCompetition(Scanner keyboard) {
        System.out.println("Competition name: ");
        setName(keyboard.nextLine());
        System.out.println("A new competition has been created!");
        setActive(true);
    }



    /**
     * Ask bill ID
     * @param keyboard  for reading the user input
     * @param dataset   for providing bill information
     * @return          a bill
     */
    public Bill AskBillID(Scanner keyboard, DataProvider dataset){
        String billId;
        Bill bill;

        while (true) {
            System.out.println("Bill ID: ");
            billId = keyboard.nextLine();
            try {

                // Check whether the bill ID is of correct format
                if (billId.length() != getIdFormat()) {
                    System.out.println("Invalid bill id! It must be a 6-digit number. " +
                            "Please try again.");
                } else {

                    bill = dataset.getBill(Integer.parseInt(billId));

                    // Check whether the bill is existing
                    if (bill == null) {
                        System.out.println("This bill does not exist. Please try again.");

                    // Check whether the member ID of the bill is existing
                    } else if (bill.getMemberId() == bill.getNoValue()) {
                        System.out.println("This bill has no member id. Please try again.");

                    // Check whether the bill amount is eligible for getting entries
                    } else if (bill.getTotalAmount() < getEligiblity()) {
                        System.out.println("This bill is not eligible for an entry. " +
                                "The total amount is smaller than $50.0.");

                    // Check whether the bill has been used for other competition
                    } else if (bill.getIfUsed() == true) {
                        System.out.println("This bill has already been used for a competition. " +
                                "Please try again.");
                    } else {
                        break;
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid bill id! It must be a 6-digit number. " +
                        "Please try again.");
            }
        }
        return bill;
    }



    /**
     * Add entries for a corresponding type of a competition
     * The way of adding entries for LuckyNumbers and RandomPick is different
     * This is the abstract class which will be overwritten in its child class
     * @param Keyboard      for reading the user input
     * @param dataLibrary   for providing bill information(e.g., Bill ID, Bill Name, etc.)
     */
    public abstract void addEntries(Scanner Keyboard, DataProvider dataLibrary);



    /**
     * Draw winners for a corresponding type of a competition
     * The way of drawing winners for LuckyNumbers and RandomPick is different
     * This is the abstract class which will be overwritten in its child class
     * @param keyboard      for reading the user input
     * @param dataLibrary   for providing member information(e.g., Member Name)
     */
    public abstract void drawWinners(Scanner keyboard, DataProvider dataLibrary);



    /**
     * Print competition information
     * This is the abstract class which will be overwritten in its child class
     * @return  a string showing the competition ID, name and type
     */
    public abstract String toString();



    /**
     * A summary report for each competition
     */
    public void report() {
        // Check if the competition is active
        String yesOrNo = "";
        if (getActive() == true) {
            yesOrNo = "yes";
        }
        else {
            yesOrNo = "no";
        }

        // Printer
        System.out.printf("\nCompetition ID: %d, name: %s, active: %s\n", getId(), getName(),
                yesOrNo);
        System.out.println("Number of entries: " + getTotalEntries());
        if (getActive()== false) {
            System.out.println("Number of winning entries: " + getTotalWinner());
            System.out.println("Total awarded prizes: " + getTotalPrize());
        }
    }


}
