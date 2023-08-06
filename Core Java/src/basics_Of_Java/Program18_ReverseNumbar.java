package basics_Of_Java;

import java.util.Scanner;

public class Program18_ReverseNumbar {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter number to reverse it:");
		int num=scan.nextInt();
		
		int rev=0;
		
		
		while(num>0) {
			
			int rem=num%10;
			rev=(rev*10)+rem;
			num=num/10;
		}
		
		System.out.println("Reversed Number is :"+rev);

	}

}
