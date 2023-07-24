package basics_Of_Java;

import java.util.Scanner;

public class Program13_MultiplecationTable {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter the number to display its table :");
		int num=scan.nextInt();
		
		for(int i=1;i<=10;i++) {
			System.out.print(num+" * "+i+" = "+num*i+"\n");
		}

	}

}
