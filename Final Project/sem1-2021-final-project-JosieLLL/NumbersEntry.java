/*
 * Student name: Jiayi Li
 * Student ID: 1097419
 * LMS username: jiayi9
 */

import java.util.Arrays;
import java.util.Scanner;

public class NumbersEntry extends Entry {

    private final int SIZE = 7;
    private final int LOW_BOUND = 1;
    private final int HIGH_Bound = 35;

    private int[] numbers = new int[SIZE];

    /**
     * Default Constructor
     */
    public NumbersEntry(){
        super();
    }



    /**
     * Constructor with entry ID
     * @param entryId   entry ID
     */
    public NumbersEntry(int entryId){
        super(entryId);
    }



    /**
     * Constructor with more information
     * @param entryId   entry ID
     * @param billId    bill ID
     * @param memberId  member ID
     */
    public NumbersEntry(int entryId, String billId, String memberId){
        super(entryId, billId, memberId);
    }



    /**
     * Setter - set the numbers of LuckyNumbers entry
     * @param numbers   numbers of LuckyNumbers entry
     */
    public void setNumbers(int[] numbers) {
        for (int i = 0; i < SIZE; i++) {
            this.numbers[i] = numbers[i];
        }
    }



    /**
     * Getter - set the numbers of LuckyNumbers entry
     * @return  numbers of LuckyNumbers entry
     */
    public int[] getNumbers() {
        return numbers;
    }



    /**
     * Create Manual Numbers
     * @param keyboard  for reading the user input
     * @return  manual numbers for this entry created
     */
    public int[] createNumbers(Scanner keyboard) {
        int[] entryNumbers;

        OuterLoop:
        while (true) {

            // Ask user to input 7 numbers within the range (1 to 35)
            System.out.println("Please enter 7 different numbers (from the range 1 to 35) " +
                    "separated by whitespace.");
            String[] nums = keyboard.nextLine().split(" ");
            entryNumbers = new int[nums.length];

            // Check if all the user inputs are numbers
            InnerLoop:
            for (int i = 0; i < nums.length; i++){
                try{
                    int num = Integer.parseInt(nums[i]);
                    entryNumbers[i] = num;

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Numbers are expected. Please try again!");
                    continue OuterLoop;
                }
            }

            // Check if there are 7 numbers rather than less or more
            if (entryNumbers.length < SIZE) {
                System.out.println("Invalid input! " +
                        "Fewer than 7 numbers are provided. Please try again!");
            } else if (entryNumbers.length > SIZE) {
                System.out.println("Invalid input! " +
                        "More than 7 numbers are provided. Please try again!");
            } else { // Correct number of numbers for each entry

                // Check if there is one number is out of range (1 to 35)
                for (int i = 0; i < entryNumbers.length; i++) {
                    if (entryNumbers[i] < LOW_BOUND || entryNumbers[i] > HIGH_Bound) {
                        System.out.println("Invalid input! " +
                                "All numbers must be in the range from 1 to 35!");
                        continue OuterLoop;
                    }
                }

                // Check if there are duplicates in this entry
                boolean duplicates = false;
                for (int i = 0; i < entryNumbers.length - 1; i++) {
                    for (int j = i + 1; j < entryNumbers.length; j++) {
                        if (entryNumbers[i] == entryNumbers[j]) {
                            duplicates = true;
                        }
                    }
                }

                // if there are duplicates, Ask user to retype the numbers
                if (duplicates == true) {
                    System.out.println("Invalid input! All numbers must be different!");
                } else { // No duplicates, numbers sorted and assigned as the final version
                    Arrays.sort(entryNumbers);
                    break OuterLoop;
                }

            }

        }
        return entryNumbers;
    }



    /**
     * Printer of the manual entry
     */
    public void printEntries() {
        System.out.printf("Entry ID: %-2d     Numbers:", getEntryId());
        for (int i = 0; i < SIZE; i++) {
            System.out.printf(" %2d", numbers[i]);
        }
        System.out.println();
    }



    /**
     * Printer of the numbers of manual entry
     */
    public void printNumbers() {
        for (int i = 0; i < SIZE; i++) {
            System.out.printf(" %2d", numbers[i]);
        }
        System.out.println();
    }



}
