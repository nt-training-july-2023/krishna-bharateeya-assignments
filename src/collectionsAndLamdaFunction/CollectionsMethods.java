package collectionsAndLamdaFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CollectionsMethods {

	public static void main(String[] args) {
		
		Collection<Integer> arr=new ArrayList<Integer>();
		
		arr.add(10);
		arr.add(7);
		arr.add(0);
		arr.add(11);
		arr.add(1);
		arr.add(4);
		arr.add(12);
		arr.add(9);
		System.out.println("List Of Element :"+arr.toString());
		
		System.out.println("collection : "+arr);
		System.out.println("The list contains 6 :"+ arr.contains(6));
		System.out.println("The list contains 9 :"+ arr.contains(9));
		
		arr.remove(9);
		System.out.println("Updated List Of Element :"+arr.toString());
		System.out.println("The list contains 9 :"+ arr.contains(9));
		
		arr.clear();
		System.out.println("List Of Element :"+arr.toString());
		arr.add(11);
		arr.add(1);
		arr.add(4);
		arr.add(12);
		System.out.println("List Of Element :"+arr.toString());
	}
	
}
