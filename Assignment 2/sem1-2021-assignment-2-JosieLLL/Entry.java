/**
 * @author: Jiayi Li
 * @studentID: 1097419
 * @username(email): jiayi9@student.unimelb.edu.au
 * @Git: https://github.com/COMP90041/sem1-2021-assignment-2-JosieLLL.git
 */

import java.util.Scanner;
import java.util.Arrays;


public class Entry {

    private int entryId;
    private int[] numbers;
    private final int SIZE = 7; //size of entry
    private final int lowBound = 1; // the lower boundary of the number range
    private final int highBound = 35; // the higher boundary of the number range

    // Constructors
    public Entry () {}

    public Entry(int id) {
        entryId = id;
        numbers = new int[SIZE];
    }

    public Entry(Scanner keyboard, int id) {
        entryId = id;
        numbers = new int[SIZE];
        createManualNumbers(keyboard);
    }


    // Getters
    public int getEntryId() {
        return entryId;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public final int getSize() {
        return SIZE;
    }

    public final int getLowBound(){ return lowBound; }

    public final int getHighBound(){ return highBound; }


    // Setters
    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public void setNumbers(int[] numbers) {
        for (int i = 0; i < SIZE; i++) {
            this.numbers[i] = numbers[i];
        }
    }


    // Create manual numbers
    public void createManualNumbers(Scanner keyboard) {

        while(true) {

            // Ask user to input 7 numbers within the range
            System.out.println("Please enter 7 different numbers (from the range 1 to 35) separated by whitespace.");
            String[] nums = keyboard.nextLine().split(" ");

            // Check if there are 7 numbers rather than less or more than 7
            if (nums.length < SIZE) {
                System.out.println("Invalid input! Fewer than 7 numbers are provided. Please try again!");
            }
            else if (nums.length > SIZE) {
                System.out.println("Invalid input! More than 7 numbers are provided. Please try again!");
            }
            else { // Correct numbers for each entry

                // Assign each number into entry
                for (int i = 0; i < SIZE; i++) {
                    this.numbers[i] = Integer.parseInt(nums[i]);
                }

                // sort the entry
                Arrays.sort(this.numbers);

                // check if there are duplicates in each entry
                boolean duplicates = false;
                for (int i = 0; i < SIZE - 1; i++) {
                    for (int j = i + 1; j < SIZE; j++) {
                        if (this.numbers[i] == this.numbers[j]) {
                            duplicates = true;
                        }
                    }
                }

                // if there are duplicates, Ask user to retype the numbers
                if (duplicates == true) {
                    System.out.println("Invalid input! All numbers must be different!");
                }
                else { // No duplicates, entry assigned
                    break;
                }
            }
        }

    }


    // Printers
    public void printEntries() {
        System.out.printf("Entry ID: %-2d     Numbers:", getEntryId());
        for (int i = 0; i < SIZE; i++) {
            System.out.printf(" %2d", numbers[i]);
        }
        System.out.println();
    }

    public void printNumbers() {
        for (int i = 0; i < SIZE; i++) {
            System.out.printf(" %2d", numbers[i]);
        }
        System.out.println();
    }




}
