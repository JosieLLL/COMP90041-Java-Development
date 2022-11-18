/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 10
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

/* This class demonstrates handling the InputMismatchException
   Jianzhong Qi, 27 January 2015
*/

import java.util.*;

public class InputMismatchExceptionDemo {
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		try {
			System.out.print("Enter the first non-negative number: ");
			int firstNumber = stdin.nextInt();

			System.out.print("Enter the second non-negative number: ");
			int secondNumber = stdin.nextInt();

			System.out.print("Your numbers are ");
			System.out.println(firstNumber + " and " + secondNumber);
		} catch(InputMismatchException e) {
			System.out.println("Please enter a positive integer.");
		}
                stdin.close();
	}
}
