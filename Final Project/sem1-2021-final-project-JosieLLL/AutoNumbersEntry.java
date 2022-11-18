/*
 * Student name: Jiayi Li
 * Student ID: 1097419
 * LMS username: jiayi9
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class AutoNumbersEntry extends NumbersEntry {

    private final int NUMBER_COUNT = 7;
    private final int MAX_NUMBER = 35;

    /**
     * Default Constructor
     */
    public AutoNumbersEntry(){ super();}



    /**
     * Constructor with entry ID
     * @param entryId   entry ID
     */
    public AutoNumbersEntry(int entryId){
        super(entryId);
    }



    /**
     * Constructor with more information
     * @param entryId   entry ID
     * @param billId    bill ID
     * @param memberId  member ID
     */
    public AutoNumbersEntry (int entryId, String billId, String memberId){
        super(entryId, billId, memberId);
    }



    /**
     * Create Automatic Numbers
     * Normal mode - no seed required
     * @return  automatic numbers for this entry created
     */
    public int[] createNumbers () {
        ArrayList<Integer> validList = new ArrayList<Integer>();
        int[] tempNumbers = new int[NUMBER_COUNT];
        for (int i = 1; i <= MAX_NUMBER; i++) {
            validList.add(i);
        }
        Collections.shuffle(validList, new Random());
        for (int i = 0; i < NUMBER_COUNT; i++) {
            tempNumbers[i] = validList.get(i);
        }
        Arrays.sort(tempNumbers);
        return tempNumbers;
    }



    /**
     * Create Automatic Numbers
     * Testing mode - seed required
     * @return  automatic numbers for this entry created
     */
    public int[] createNumbers (int seed) {
        ArrayList<Integer> validList = new ArrayList<Integer>();
	    int[] tempNumbers = new int[NUMBER_COUNT];
        for (int i = 1; i <= MAX_NUMBER; i++) {
    	    validList.add(i);
        }
        Collections.shuffle(validList, new Random(seed));
        for (int i = 0; i < NUMBER_COUNT; i++) {
    	    tempNumbers[i] = validList.get(i);
        }
        Arrays.sort(tempNumbers);
        return tempNumbers;
    }



    /**
     * Printer of the automatic entry
     */
    @Override
    public void printEntries() {
        int[] autoNumbers = new int[NUMBER_COUNT];
        autoNumbers = getNumbers();
        System.out.printf("Entry ID: %-2d     Numbers:", getEntryId());
        for (int i = 0; i < NUMBER_COUNT; i++) {
            System.out.printf(" %2d", autoNumbers[i]);
        }
        System.out.print(" [Auto]");
        System.out.println();
    }



    /**
     * Printer of the numbers of automatic entry
     */
    @Override
    public void printNumbers() {
        int[] autoNumbers = new int[NUMBER_COUNT];
        autoNumbers = getNumbers();
        for (int i = 0; i < NUMBER_COUNT; i++) {
            System.out.printf(" %2d", autoNumbers[i]);
        }
        System.out.print(" [Auto]");
        System.out.println();
    }


}
