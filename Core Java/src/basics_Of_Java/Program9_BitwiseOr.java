package basics_Of_Java;

import java.util.Scanner;

public class Program9_BitwiseOr {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter the first value :");
		int a=scan.nextInt();
		
		System.out.println("Enter the second Value :");
		int b=scan.nextInt();
		
		int result= a|b;
		
		System.out.println("Bitwise Or : "+result);

	}

}
