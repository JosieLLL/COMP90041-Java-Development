/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 10
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

import java.util.*;

public class WhileLoopAndException {
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
                boolean done = false;

                while (!done) {
		try {
			System.out.print("Enter the first non-negative number: ");
			int firstNumber = stdin.nextInt();
			if(firstNumber < 0) {
				throw new NegativeNumberException();
			}
			
			System.out.print("Enter the second non-negative number: ");
			int secondNumber = stdin.nextInt();
			if(secondNumber < 0) {
				throw new NegativeNumberException();
			}
			
			System.out.print("Your numbers are ");
			System.out.println(firstNumber + " and " + secondNumber);
                        done = true;
		} catch(InputMismatchException e) {
			System.out.println("Please enter a positive integer.");
		} catch(NegativeNumberException e) {
			System.out.println(e.getMessage());
			System.out.println("Please enter a positive integer.");
		} finally {
                        stdin.nextLine();
                }
                }
                stdin.close();
	}
}
