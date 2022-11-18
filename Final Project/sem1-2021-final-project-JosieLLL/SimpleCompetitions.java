/*
 * Student name: Jiayi Li
 * Student ID: 1097419
 * LMS username: jiayi9
 */

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class SimpleCompetitions {

    private final int USER_OPTION_LENGTH = 1;

    private int countCompetition;
    private LuckyNumbersCompetition luckyNumbersCompetition = new LuckyNumbersCompetition();
    private RandomPickCompetition randomPickCompetition = new RandomPickCompetition();
    private boolean testingMode;
    private ArrayList<Competition> competitions = new ArrayList<Competition>();

    private static DataProvider dataProvider;

    /**
     * Print menu
     */
    public void menu() {
        System.out.println("Please select an option. Type 5 to exit.");
        System.out.println("1. Create a new competition");
        System.out.println("2. Add new entries");
        System.out.println("3. Draw winners");
        System.out.println("4. Get a summary report");
        System.out.println("5. Exit");
    }



    /**
     * Ask user whether load competitions from a certain file
     * @param keyboard  for reading the user option
     */
    public void ifLoadCompetitions(Scanner keyboard){
        OuterLoop_Asking:
        while (true) {
            System.out.println("Load competitions from file? (Y/N)?");
            String yesOrNo = keyboard.nextLine();
            if (yesOrNo.length() == USER_OPTION_LENGTH) {
                switch (yesOrNo.charAt(0)) {
                    case 'Y':
                    case 'y':
                        System.out.println("File name:");
                        String fileName = keyboard.nextLine();
                        try{
                            loadCompetitions(fileName);
                        } catch (DataAccessException e){
                            System.out.println(e.getMessage());
                            break;
                        }
                        break OuterLoop_Asking;

                    case 'N':
                    case 'n':
                        modeChoice(keyboard);
                        break OuterLoop_Asking;

                    default:
                        System.out.println("Unsupported option. Please try again!");
                }
            } else {
                System.out.println("Unsupported option. Please try again!");
            }
        }
    }



    /**
     * Ask user what competition mode would be run
     * @param keyboard  for reading the user option
     */
    public void modeChoice (Scanner keyboard){
        OuterLoop_AskingMode:
        while (true){
            System.out.println("Which mode would you like to run? " +
                    "(Type T for Testing, and N for Normal mode):");
            String modeChoice = keyboard.nextLine();
            if (modeChoice.length() == USER_OPTION_LENGTH){
                switch(modeChoice.charAt(0)){
                    case 'T':
                    case 't':
                        testingMode = true;
                        break OuterLoop_AskingMode;

                    case 'N':
                    case 'n':
                        testingMode = false;
                        break OuterLoop_AskingMode;

                    default:
                        System.out.println("Invalid mode! Please choose again.");
                }
            } else {
                System.out.println("Invalid mode! Please choose again.");
            }
        }
    }



    /**
     * Load the member and bill data from specific files
     * @param keyboard  for reading the file name
     * @return          member and bill information
     */
    public DataProvider readingData (Scanner keyboard) {

        DataProvider dataProvider = null;

        System.out.println("Member file: ");
        String memberFile = keyboard.nextLine();
        System.out.println("Bill file: ");
        String billFile = keyboard.nextLine();

        try {
            dataProvider = new DataProvider(memberFile, billFile);

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println("Goodbye!");
            System.exit(0);
        } catch (DataFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Goodbye!");
            System.exit(0);
        }

        return dataProvider;
    }



    /**
     * Ask user what type of competition would they like to run
     * There are two types (LuckNumbers and RandomPick) offered
     * Once the user makes a choice, the corresponding type of competition will be created
     * If there is an active competition already active, no more competition can be created
     * @param keyboard  for reading the user option
     */
    public void addNewCompetition(Scanner keyboard) {

        if (luckyNumbersCompetition.getActive() == false &&
                randomPickCompetition.getActive() == false) {

            OuterLoop_AskingType:
            while (true){
                System.out.println("Type of competition (L: LuckyNumbers, R: RandomPick)?:");
                String type = keyboard.nextLine();
                if (type.length() == USER_OPTION_LENGTH){
                    switch (type.charAt(0)){
                        case 'L':
                        case 'l':
                            countCompetition++;
                            luckyNumbersCompetition = new LuckyNumbersCompetition(keyboard,
                                    countCompetition, testingMode);
                            System.out.println(luckyNumbersCompetition.toString());
                            competitions.add(luckyNumbersCompetition);
                            break OuterLoop_AskingType;

                        case 'R':
                        case 'r':
                            countCompetition++;
                            randomPickCompetition = new RandomPickCompetition(keyboard,
                                    countCompetition, testingMode);
                            System.out.println(randomPickCompetition.toString());
                            competitions.add(randomPickCompetition);
                            break OuterLoop_AskingType;

                        default:
                            System.out.println("Invalid competition type! Please choose again.");
                    }
                } else {
                    System.out.println("Invalid competition type! Please choose again.");
                }
            }


        } else {
            System.out.println("There is an active competition. " +
                    "SimpleCompetitions does not support concurrent competitions!");
        }

    }



    /**
     * Add entries for the competition
     * If there is no active competition, no entry can be added
     * @param keyboard  for reading the user input
     * @param dataPool  for providing the bill information of the entry
     */
    public void addEntries(Scanner keyboard, DataProvider dataPool){
        if (luckyNumbersCompetition.getActive() == false &&
                randomPickCompetition.getActive() == false){
            System.out.println("There is no active competition. Please create one!");
        } else if (luckyNumbersCompetition.getActive() == true) {
            luckyNumbersCompetition.addEntries(keyboard, dataPool);
        } else if (randomPickCompetition.getActive() == true){
            randomPickCompetition.addEntries(keyboard, dataPool);
        }
    }



    /**
     * Draw winner for the competition
     * If there is no active competition, no entry can be added
     * @param keyboard  for reading the user input
     * @param dataPool  for providing the member information of the entry
     */
    public void drawWinner(Scanner keyboard, DataProvider dataPool){
        if (luckyNumbersCompetition.getActive() == false &&
                randomPickCompetition.getActive() == false){
            System.out.println("There is no active competition. Please create one!");
        } else if (luckyNumbersCompetition.getActive() == true) {
            luckyNumbersCompetition.drawWinners(keyboard, dataPool);
        } else if (randomPickCompetition.getActive() == true){
            randomPickCompetition.drawWinners(keyboard, dataPool);
        }
    }



    /**
     * Print a summary report for competitions
     * If there is no competition created, no report will be shown
     */
    public void report(){
        if (competitions.isEmpty() == true){
            System.out.println("No competition has been created yet!");
        } else {
            int activeCompetition = 0;
            int completedCompetition = 0;

            // Count how many competitions completed/active
            for (Competition competition: competitions) {
                if (competition.getActive() == true) {
                    activeCompetition++;
                }
                else {
                    completedCompetition++;
                }
            }

            // Printer
            System.out.println("----SUMMARY REPORT----");
            System.out.println("+Number of completed competitions: " + completedCompetition);
            System.out.println("+Number of active competitions: " + activeCompetition);
            for (Competition competition: competitions) {
                competition.report();
            }

        }
    }



    /**
     * Ask user whether to save competitions to a certain file
     * @param keyboard  for reading the user option
     */
    public void ifSaveCompetitions(Scanner keyboard){
        OuterLoop_AskingIfSave:
        while (true){
            System.out.println("Save competitions to file? (Y/N)?");
            String ifSave = keyboard.nextLine();
            if (ifSave.length() == USER_OPTION_LENGTH){
                switch (ifSave.charAt(0)){
                    case 'Y':
                    case 'y':
                        System.out.println("File name:");
                        String fileName = keyboard.nextLine();
                        saveCompetitions(fileName);
                        break OuterLoop_AskingIfSave;

                    case 'N':
                    case 'n':
                        break OuterLoop_AskingIfSave;

                    default:
                        System.out.println("Unsupported option. Please try again!");
                }
            } else {
                System.out.println("Unsupported option. Please try again!");
            }

        }

    }



    /**
     * Save current competition state to a file designated by a user
     * After saving competitions, the bill file will also be updated
     * @param fileName  the file that holds the state of competition
     */
    public void saveCompetitions(String fileName){
        // Save competitions
        ObjectOutputStream outputStream = null;
        while (true){
            try {
                outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
                CompetitionState competitionState = new CompetitionState(countCompetition,
                        testingMode, luckyNumbersCompetition, randomPickCompetition, competitions);
                outputStream.writeObject(competitionState);
                outputStream.close();
                System.out.println("Competitions have been saved to file.");
                break;
            } catch (FileNotFoundException e) {
                File file = new File(fileName);
            } catch (IOException e) {
                System.out.println("Error writing to " + fileName);
                break;
            }
        }

        // Update bill file
        try {
            dataProvider.updateBill();
            System.out.println("The bill file has also been automatically updated.");
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to update the bill file");
        }

    }



    /**
     * Load previous competitions from the file provided by a user
     * Continue the following competitions based on the previous competition state
     * @param fileName              the file that holds the previous competitions
     * @throws DataAccessException  occurs when no competitions found in the file
     */
    public void loadCompetitions(String fileName) throws DataAccessException{
        ObjectInputStream inputStream = null;
        CompetitionState competitionState = null;

        try{
            inputStream = new ObjectInputStream(new FileInputStream(fileName));

            try{
                competitionState = (CompetitionState) inputStream.readObject();
            } catch (ClassNotFoundException e){
                throw new ClassNotFoundException();
            }

            countCompetition = competitionState.countCompetition;
            luckyNumbersCompetition = competitionState.luckyNumbersCompetition;
            randomPickCompetition = competitionState.randomPickCompetition;
            testingMode = competitionState.testingMode;
            competitions = competitionState.competitions;

            inputStream.close();

        } catch (FileNotFoundException e){
            System.out.println("The file cannot be found/opened!");
            System.out.println("Goodbye!");
            System.exit(0);
        } catch (IOException e){
            System.out.println("Error to load competitions!");
            System.out.println("Goodbye!");
            System.exit(0);
        } catch (ClassNotFoundException e){
            throw new DataAccessException("No competition has been created yet! " +
                    "please choose again!");
        }

    }



    /**
     * Main program that uses the main SimpleCompetitions class
     * Control competition flow
     * @param args  main method argument
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        SimpleCompetitions sc = new SimpleCompetitions();
        int userOption = 0;

        System.out.println("----WELCOME TO SIMPLE COMPETITIONS APP----");

        sc.ifLoadCompetitions(keyboard);
        dataProvider = sc.readingData(keyboard);

        MainLoop:
        while (true){
            sc.menu();
            try {
                userOption = keyboard.nextInt();
                switch (userOption){
                    case 1:
                        keyboard.nextLine(); // skip the new line after user input
                        sc.addNewCompetition(keyboard);
                        break;

                    case 2:
                        keyboard.nextLine(); // skip the new line after user input
                        sc.addEntries(keyboard, dataProvider);
                        break;

                    case 3:
                        keyboard.nextLine(); // skip the new line after user input
                        sc.drawWinner(keyboard, dataProvider);
                        break;

                    case 4:
                        keyboard.nextLine(); // skip the new line after user input
                        sc.report();
                        break;

                    case 5:
                        keyboard.nextLine(); // skip the new line after user input
                        sc.ifSaveCompetitions(keyboard);
                        break MainLoop;

                    default:
                        System.out.println("Unsupported option. Please try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("A number is expected. Please try again.");
                keyboard.nextLine();
            }
        }
        System.out.println("Goodbye!");
        keyboard.close();
    }
}



// Shortcut class for saving objects into a file
class CompetitionState implements Serializable {

    // Protected instance variables can be accessed in main method when loading current competition
    // state from a file
    protected int countCompetition;
    protected LuckyNumbersCompetition luckyNumbersCompetition;
    protected RandomPickCompetition randomPickCompetition;
    protected boolean testingMode;
    protected ArrayList<Competition> competitions;

    /**
     * Constructor a class holding all current competition state
     * @param countCompetition          the number of total competitions implemented
     * @param testingMode               current competition mode
     * @param luckyNumbersCompetition   current luckyNumbers competition
     * @param randomPickCompetition     current RandomPick competition
     * @param competitions              history of total competitions implemented
     */
    public CompetitionState (int countCompetition, boolean testingMode,
                             LuckyNumbersCompetition luckyNumbersCompetition,
                             RandomPickCompetition randomPickCompetition,
                             ArrayList<Competition> competitions){

        this.countCompetition = countCompetition;
        this.testingMode = testingMode;
        this.luckyNumbersCompetition = luckyNumbersCompetition;
        this.randomPickCompetition = randomPickCompetition;
        this.competitions = competitions;
    }

}
