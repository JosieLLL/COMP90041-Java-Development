/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 3
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

/* Program to display the denominations of change for a Point-of-Sale System
 */

import java.util.Scanner;

public class Denominations {
	private static Scanner scanner;
	
	public static void main (String[] args) {
		scanner = new Scanner(System.in);
		final double EPSILON = 0.000001;
		// variables to store the change and amount left to be paid.		
		double change, remainder;
		
		// keep track of notes 
		int fifties, twenties, tens, fives, twos, ones;

        // keep track of coins
        int fifty_cents, twenty_cents, ten_cents, five_cents;
		
		System.out.print("Please enter the change (without $): ");
		change = scanner.nextDouble();
		
		// to tackle any rounding off errors
		change += EPSILON;
		
		fifties = (int)(change / 50);		
		remainder = change % 50;		
		
		twenties = (int)(remainder / 20);
		remainder = remainder % 20;
	
		tens = (int)(remainder / 10);
		remainder = remainder % 10;
		
		fives = (int)(remainder / 5);
		remainder = remainder % 5;
		
		twos = (int)(remainder / 2);
		remainder = remainder % 2;
		
		ones = (int)(remainder / 1);
		remainder = remainder % 1;
		
		fifty_cents = (int)(remainder / 0.5);
		remainder = remainder % 0.5;
				
		twenty_cents = (int)(remainder / 0.2);
		remainder = remainder % 0.2;
				
		ten_cents = (int)(remainder / 0.10);
		remainder = remainder % 0.10;
				
		five_cents = (int)(remainder / 0.05);
		remainder = remainder % 0.05;
				
		System.out.println("Please give the customer: ");
		System.out.println("$50\t" + fifties);
		System.out.println("$20\t" + twenties);
		System.out.println("$10\t" + tens);
		System.out.println("$5\t" + fives);
		System.out.println("$2\t" + twos);
		System.out.println("$1\t" + ones);
		System.out.println("50c\t" + fifty_cents);
		System.out.println("20c\t" + twenty_cents);
		System.out.println("10c\t" + ten_cents);
		System.out.println("5c\t" + five_cents);		
	}
}
