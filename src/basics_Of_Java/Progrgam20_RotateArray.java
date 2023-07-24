package basics_Of_Java;

import java.util.Scanner;

public class Progrgam20_RotateArray {

	public static void main(String args[]) {
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Ente the Number of element :");
		int num=scan.nextInt();
		
		System.out.println("Enter "+num+" element :");
		int arr[]=new int[num];
		for(int i=0;i<num;i++) {
			arr[i]=scan.nextInt();
		}
		
		if(arr.length>2) {
			int temp=arr[0];
			int temp2=arr[1];
			
			for(int i=0,j=2;i<arr.length&&j<arr.length;i++,j++) {
				arr[i]=arr[j];
			}
			arr[arr.length-2]=temp;
			arr[arr.length-1]=temp2;
		}
		
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+"\t");
		}
	}
}
