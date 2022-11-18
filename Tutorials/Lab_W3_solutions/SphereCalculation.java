/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 3
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

/* Write a program that reads the radius of a sphere 
   and prints its volume and surface area.  
   Jianzhong Qi, 10 February 2016
*/

import java.util.Scanner;

public class SphereCalculation {
	private static Scanner scanner;

	public static void main (String[] args) {
		scanner = new Scanner(System.in);
		double radius = scanner.nextDouble();
		System.out.println("Volume : " + 4.0 / 3.0 * Math.PI
				* Math.pow(radius, 3));
		System.out.println("Surface area: " + 4 * Math.PI 
				* radius * radius);
	}
}
