package basics_Of_Java;

import java.util.Scanner;

public class Program22_Average {

	public static void main(String args[]) {
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Ente the Number of element :");
		int num=scan.nextInt();
		
		System.out.println("Enter "+num+" element :");
		int arr[]=new int[num];
		for(int i=0;i<num;i++) {
			arr[i]=scan.nextInt();
		}
		
		int sum=0;
		
		for(int i=0;i<arr.length;i++) {
			sum=sum+arr[i];
		}
		
		float avg= sum/num;
		System.out.println("Average :"+avg);
		}

}
