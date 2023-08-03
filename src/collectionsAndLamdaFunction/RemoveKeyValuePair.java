package collectionsAndLamdaFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class RemoveKeyValuePair {

private Map<Integer,String> map=new HashMap<>();
	
	public void addElement(int key,String value) {
		map.put(key, value);
	}
	
	public void printAllKeyValue() {
		System.out.println("List of Elements :");
		
		for(Entry<Integer, String> entry : map.entrySet()) {
			System.out.println("Key :"+entry.getKey()+", Value :"+entry.getValue());
		}
		
	}
	
	public void removeKeyValuePair(int key) {
		int flag=1;
		for(Entry<Integer, String> entry : map.entrySet()) {
			
			if(entry.getKey().equals(key)) {
				map.remove(key);
				flag=0;
				System.out.println("Key Deleted Successfully.");
				break;
			}
		}
		if(flag==1) {
			System.out.println("Given is Key Not Found in the List.");
		}
		
	}
	
public static void main(String[] args) {
		
	Scanner scan = new Scanner(System.in);
	RemoveKeyValuePair map=new RemoveKeyValuePair();
	
	System.out.println("Enter the Key to remove from the map:");
    int key = scan.nextInt();
		
		map.addElement(131, "Amit");
		map.addElement(132, "sumit");
		map.addElement(133, "karan");
		map.addElement(134, "ranjeet");
		map.addElement(125, "arif");
		
		System.out.println("List Of element :\n");
		map.printAllKeyValue();
		map.removeKeyValuePair(key);
			
	}

}
