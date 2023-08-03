package collectionsAndLamdaFunction;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class LinkHashSet {

public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		LinkedHashSet<Integer> hashSet=new LinkedHashSet<Integer>();
		
		System.out.println("Enter No. Of  Element :");
		int size=scan.nextInt();
		System.out.println("Enter "+size+" elements :");
		for(int i=0;i<size;i++) {
			hashSet.add(scan.nextInt());
		}
		System.out.println("List Of All Unique element:\n"+hashSet);
		scan.close();
		
	}
}
