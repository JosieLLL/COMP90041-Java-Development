/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 3
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

/* Write a program that reads two floating point numbers and 
   print their sum, difference, and product.
   Jianzhong Qi, 10 February 2016
*/

import java.util.Scanner;

public class FloatPointCalculation {
	private static Scanner scanner;

	public static void main (String[] args) {
		float numberA;
		float numberB;
		scanner = new Scanner(System.in);
		numberA = scanner.nextFloat();
		numberB = scanner.nextFloat();
		String displayA = String.valueOf(numberA);
		String displayB = String.valueOf(numberB);
		System.out.println(displayA + " + " + displayB + " = "
				+ (numberA + numberB));
		System.out.println(displayA + " - " + displayB + " = "
				+ (numberA - numberB));		
		System.out.println(displayA + " x " + displayB + " = "
				+ (numberA * numberB));
	};
}
