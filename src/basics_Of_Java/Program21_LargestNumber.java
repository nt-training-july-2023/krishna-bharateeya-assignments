package basics_Of_Java;

import java.util.Scanner;

public class Program21_LargestNumber {
	
	public static void main(String args[]) {
	Scanner scan=new Scanner(System.in);
	
	System.out.println("Ente the Number of element :");
	int num=scan.nextInt();
	
	System.out.println("Enter "+num+" element :");
	int arr[]=new int[num];
	for(int i=0;i<num;i++) {
		arr[i]=scan.nextInt();
	}
	
	int max=arr[0];
	
	for(int i=1;i<arr.length;i++) {
		if(arr[i]>max)
			max=arr[i];
	}
	System.out.println("Largest number is :"+max);
	}

}
