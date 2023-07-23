package Programs;

import java.util.Scanner;

public class Program11_Left_Shift {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter the Number :");
		int num=scan.nextInt();
		
		System.out.println("Enter the Bit value to shift Left :");
		int b=scan.nextInt();
		
		int result=num<<b;
		System.out.println("Left Shift : "+result);

	}

}
