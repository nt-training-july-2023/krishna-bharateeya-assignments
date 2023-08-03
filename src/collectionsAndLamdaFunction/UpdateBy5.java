package collectionsAndLamdaFunction;

import java.util.ArrayList;
import java.util.Scanner;

public class UpdateBy5 {
	
	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		ArrayList<Integer> arr=new ArrayList<Integer>();
		
		System.out.println("Enter Number Of Element :");
		int size=scan.nextInt();
		
		for(int i=0;i<size;i++) {
			arr.add(scan.nextInt());
		}
		System.out.println("List Before Update :\n"+arr);
		for(int i=0;i<size;i++) {
			if(arr.get(i)>50) {
				arr.set(i, 5);
			}
		}
		System.out.println("List After Update :\n"+arr);
	}


}
