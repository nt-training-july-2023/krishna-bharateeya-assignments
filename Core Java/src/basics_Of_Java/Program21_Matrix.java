package basics_Of_Java;

import java.util.Scanner;

public class Program21_Matrix {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter the Number of Row :");
		int row=scan.nextInt();
		
		System.out.println("Enter the Number of coloumn :");
		int col=scan.nextInt();
		
		System.out.println("Enter the "+row+" X "+col+"elements for First matrix:");
		
		int arr1[][]=new int[row][col];
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				arr1[i][j]=scan.nextInt();
			}
		}
		
		
		System.out.println("Enter the Number of Row for second matrix:");
		int row2=scan.nextInt();
		
		System.out.println("Enter the Number of coloumn second matrix:");
		int col2=scan.nextInt();
		
		int arr2[][]=new int[row2][col2];
		System.out.println("Enter the "+row2+" X "+col2+"elements for second matrix:");
		
		for(int i=0;i<row2;i++) {
			for(int j=0;j<col2;j++) {
				arr2[i][j]=scan.nextInt();
			}
		}
		
		
		
		System.out.println("this is First matrix");
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				System.out.print(arr1[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println("this is second matrix");
		for(int i=0;i<row2;i++) {
			for(int j=0;j<col2;j++) {
				System.out.print(arr2[i][j]+"\t");
			}
			System.out.println();
		}
		
		int result[][]=new int [row][col];
		
		
		for(int i=0;i<row2;i++) {
			for(int j=0;j<col2;j++) {
				result[i][j]=arr1[i][j]+arr2[j][i];
			}
			
		}
		
		System.out.println("the resultant matrix is :");
		for(int i=0;i<row2;i++) {
			for(int j=0;j<col2;j++) {
				System.out.print(result[i][j]+"\t");
			}
			System.out.println();
		}
		
	}
}
