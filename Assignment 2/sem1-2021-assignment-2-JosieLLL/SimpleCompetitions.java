/**
 * @author: Jiayi Li
 * @studentID: 1097419
 * @username(email): jiayi9@student.unimelb.edu.au
 * @Git: https://github.com/COMP90041/sem1-2021-assignment-2-JosieLLL.git
 */

import java.util.Scanner;
import java.util.ArrayList;

public class SimpleCompetitions {

    private final char NORMAL = 'N'; // system mode - normal
    private final char TEST = 'T'; // system mode - testing

    private int countCompetition; // count the number of competitions in the system

    private static char mode; // holding user input for the system mode

    // holding all the competitions in the system
    private static ArrayList<Competition> totalCompetitions = new ArrayList<Competition>();


    // Ask user to choose a system mode
    public void modeChoice(Scanner keyboard) {
        while(true) {
            System.out.println("Which mode would you like to run? (Type T for Testing, and N for Normal mode):");
            mode = keyboard.next().toUpperCase().charAt(0);
            if (mode != NORMAL && mode != TEST) {
                System.out.println("Invalid mode! Please choose again.");
            }
            else {
                break;
            }
        }
    }


    // Print menu
    public void menu() {
        System.out.println("Please select an option. Type 5 to exit.");
        System.out.println("1. Create a new competition");
        System.out.println("2. Add new entries");
        System.out.println("3. Draw winners");
        System.out.println("4. Get a summary report");
        System.out.println("5. Exit");
    }


    // Add new competition in the system
    public Competition addNewCompetition(Scanner keyboard) {
        countCompetition++;
        Competition newCompetition = new Competition(keyboard, countCompetition);
        return newCompetition;
    }


    // Print system report
    public void report(ArrayList<Competition> competitions) {
        int activeCompetition = 0;
        int completedCompetition = 0;

        System.out.println("----SUMMARY REPORT----");

        // Count how many competitions completed/active
        for (Competition comp: competitions) {
            if (comp.getIfActive() == true) {
                activeCompetition++;
            }
            else {
                completedCompetition++;
            }
        }

        // Printer
        System.out.println("+Number of completed competitions: " + completedCompetition);
        System.out.println("+Number of active competitions: " + activeCompetition);
        for (Competition comp: competitions) {
            comp.report();
        }
    }

    /**
     * Main program that uses the main SimpleCompetitions class
     * @param args main program arguments
     */
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        SimpleCompetitions sc = new SimpleCompetitions();
        Competition newCompetition = new Competition();

        System.out.println("----WELCOME TO SIMPLE COMPETITIONS APP----");
        sc.modeChoice(keyboard);

        mainLoop:
        while(true) {
            sc.menu();
            int option = keyboard.nextInt();
            switch(option) {
                case 1:
                    if (newCompetition.getIfActive() == false) {
                        newCompetition = sc.addNewCompetition(keyboard);
                        totalCompetitions.add(newCompetition);
                    }
                    else {
                        System.out.println("There is an active competition. SimpleCompetitions does not support concurrent competitions!");
                    }
                    break;


                case 2:
                    if (newCompetition.getIfActive() == false) {
                        System.out.println("There is no active competition. Please create one!");
                    }
                    else {
                        newCompetition.addEntries(keyboard, mode);
                    }
                    break;


                case 3:
                    if (newCompetition.getIfActive() == false) {
                        System.out.println("There is no active competition. Please create one!");
                    }
                    else {
                        newCompetition.drawWinners(keyboard, mode);
                    }
                    break;


                case 4:
                    if (totalCompetitions.isEmpty() == true) {
                        System.out.println("No competition has been created yet!");
                    }
                    else {
                        sc.report(totalCompetitions);;
                    }
                    break;


                case 5:
                    break mainLoop;


                default:
                    System.out.println("Unsupported option. Please try again!");
            }
        }
        System.out.println("Goodbye!");
        keyboard.close();
    }
}
