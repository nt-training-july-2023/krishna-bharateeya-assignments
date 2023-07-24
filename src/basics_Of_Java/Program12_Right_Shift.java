package basics_Of_Java;

import java.util.Scanner;

public class Program12_Right_Shift {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter the Number :");
		int num=scan.nextInt();
		
		System.out.println("Enter the Bit value to shift Right :");
		int b=scan.nextInt();
		
		int result=num >> b;
		System.out.println("Right Shift : "+result);

	}

}
