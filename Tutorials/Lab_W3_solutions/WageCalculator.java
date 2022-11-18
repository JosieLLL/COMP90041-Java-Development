/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 3
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

/* Write a program that calculates the total wages based on the number 
   of hours worked.
   Jianzhong Qi, 10 February 2016
*/

public class WageCalculator {
	public static void main(String[] args) {
		int hours = Integer.parseInt(args[0]);
		double wages = hours > 40 ? 40 * 8.25 + (hours - 40) * 8.25 * 1.5
				: hours * 8.25;
		System.out.println("Wages: " + wages);
	}
}
