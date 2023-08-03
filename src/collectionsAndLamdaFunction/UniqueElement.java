package collectionsAndLamdaFunction;

import java.util.HashSet;
import java.util.Scanner;

public class UniqueElement {
	
		public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		HashSet<Integer> hashSet=new HashSet<Integer>();
		
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
