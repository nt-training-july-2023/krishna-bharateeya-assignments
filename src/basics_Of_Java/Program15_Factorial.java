package basics_Of_Java;

import java.util.Scanner;

public class Program15_Factorial {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter number to find Factorial :");
		int num=scan.nextInt();
		
		int fact=1;
//		for(int i=num;i>0;i--) {
//			
//			fact=(fact*i);
//		}
		for(int i=1;i<=num;i++) {
			
			fact=(fact*i);
		}

		System.out.println("Factorial Of "+num+" : "+fact);
	}

}
