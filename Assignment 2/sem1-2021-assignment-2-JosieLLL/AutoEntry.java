/**
 * @author: Jiayi Li
 * @studentID: 1097419
 * @username(email): jiayi9@student.unimelb.edu.au
 * @Git: https://github.com/COMP90041/sem1-2021-assignment-2-JosieLLL.git
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class AutoEntry extends Entry {

    // Constructor
    public AutoEntry(int id) {
        super(id);
    }


    // Normal mode - no seed required
    public int[] createNumbers () {
        ArrayList<Integer> validList = new ArrayList<Integer>();
        int[] tempNumbers = new int[getSize()];
        for (int i = getLowBound(); i <= getHighBound(); i++) {
            validList.add(i);
        }
        Collections.shuffle(validList, new Random());
        for (int i = 0; i < getSize(); i++) {
            tempNumbers[i] = validList.get(i);
        }
        Arrays.sort(tempNumbers);
        return tempNumbers;
    }


    // Testing mode - seed required
    public int[] createNumbers (int seed) {
        ArrayList<Integer> validList = new ArrayList<Integer>();
        int[] tempNumbers = new int[getSize()];
        for (int i = getLowBound(); i <= getHighBound(); i++) {
            validList.add(i);
        }
        Collections.shuffle(validList, new Random(seed));
        for (int i = 0; i < getSize(); i++) {
            tempNumbers[i] = validList.get(i);
        }
        Arrays.sort(tempNumbers);
        return tempNumbers;
    }


    // Printers
    @Override
    public void printEntries() {
        int[] autoNumbers = new int[getSize()];
        autoNumbers = getNumbers();
        System.out.printf("Entry ID: %-2d     Numbers:", getEntryId());
        for (int i = 0; i < getSize(); i++) {
            System.out.printf(" %2d", autoNumbers[i]);
        }
        System.out.print(" [Auto]");
        System.out.println();
    }

    @Override
    public void printNumbers() {
        int[] autoNumbers = new int[getSize()];
        autoNumbers = getNumbers();
        for (int i = 0; i < getSize(); i++) {
            System.out.printf(" %2d", autoNumbers[i]);
        }
        System.out.print(" [Auto]");
        System.out.println();
    }



}
