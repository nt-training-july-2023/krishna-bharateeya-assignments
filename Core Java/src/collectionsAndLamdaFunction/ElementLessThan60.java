package collectionsAndLamdaFunction;

import java.util.ArrayList;
import java.util.Scanner;

public class ElementLessThan60 {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		ArrayList<Integer> arr=new ArrayList<Integer>();
		
		System.out.println("Enter No. Of  Element :");
		int size=scan.nextInt();
		System.out.println("Enter "+size+" elements :");
		for(int i=0;i<size;i++) {
			arr.add(scan.nextInt());
		}
		System.out.println("List Before Update :\n"+arr);
		for(int i=0;i<size;i++) {
			if(arr.get(i)>60) {
				arr.remove(i);
				size--;
			}
		}
		System.out.println("List Below 60 :\n"+arr);
	}
}
