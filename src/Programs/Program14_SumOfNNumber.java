package Programs;

import java.util.Scanner;

public class Program14_SumOfNNumber {

	public static void main(String args[]) {
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter number to find sum :");
		int num=scan.nextInt();
		
		int sum=0;
		
		for(int i=1;i<=num;i++) {
		
			sum+=i;
		}
		System.out.println("Sum Of first "+num+" numbers: "+sum);
	}
}
