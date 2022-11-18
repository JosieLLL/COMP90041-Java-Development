/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 3
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

/* The video game machines at your local arcade output coupons according 
 * to how well you play the game. You can redeem 10 coupons for a candy bar 
 * or 3 coupons for a gumball. You prefer candy bars to gumballs. Write a 
 * Java program that reads the number of coupons you have and outputs how 
 * many candy bars and gumballs you can get if you spend all of your coupons 
 * on candy bars first, and any remaining coupons on gumballs. 
 */

import java.util.Scanner;

public class CouponRedeemer{
	private static Scanner scanner;
	private static final double PRICE_CANDYBARS = 10.0, PRICE_GUMBALLS = 3.0;
	
	public static void main (String[] args) {
		scanner = new Scanner(System.in);
		int numCoupons, numCandybars = 0, numGumballs = 0;
		int remainderCoupons = 0;
		
		System.out.print("How many coupons do you have: ");
		numCoupons = scanner.nextInt();
				
		numCandybars = (int)(numCoupons / PRICE_CANDYBARS);
		remainderCoupons = (int) (numCoupons % PRICE_CANDYBARS);
		numGumballs = (int)(remainderCoupons / PRICE_GUMBALLS);
		remainderCoupons = (int) (remainderCoupons % PRICE_GUMBALLS);
		
		
		System.out.println("With " + numCoupons + " coupon(s) " + 
			" you can get " + numCandybars + " candy bar(s) and " + numGumballs + " gumball(s).");
			
		System.out.println("This will leave you with " + remainderCoupons + " coupon(s).");				
	}
}
