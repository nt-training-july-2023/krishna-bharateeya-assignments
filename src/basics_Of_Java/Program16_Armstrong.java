package basics_Of_Java;

import java.util.Scanner;

public class Program16_Armstrong {

	public static void main(String[] args) {
		

		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter number to find to check Armstrong mumber :");
		int num=scan.nextInt();
		
		int temp=num;
		int arm=0;
		
		
		while(num>0) {
		
			int r=num%10;
			arm=arm+(r*r*r);
			num=num/10;
			
		}
		System.out.println(arm);
		if(arm==temp)
			System.out.println("this is a armstrong number");
		else
			System.out.println("This is not a armstrong number");
	}

}
