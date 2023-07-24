package basics_Of_Java;

import java.util.Scanner;

public class Program19_Palindrome {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter number to Check palindrome :");
		int num=scan.nextInt();
		
		int temp=num;
		int rev=0;
		
		
		while(num>0) {
			
			int rem=num%10;
			rev=(rev*10)+rem;
			num=num/10;
		}
		
		if(rev==temp)
			System.out.println("Number is Palindrome");
		else
			System.out.println("Number is not Palondrome");

	}
}
