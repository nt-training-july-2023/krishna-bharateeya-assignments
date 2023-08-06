package collectionsAndLamdaFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IntegerCollection {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		ArrayList<Integer> element=new ArrayList<Integer>();
		System.out.println("Enter 20 Element :");
		
		for(int i=0;i<20;i++) {
			element.add(scan.nextInt());
		}
		System.out.println("List Of Entered Element :\n"+element);
		
		Collections.reverse(element);
		System.out.println("Reverse List :\n"+element);
	}
}
