package collectionsAndLamdaFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RetriveAllKeyValue {
	
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
	
public static void main(String[] args) {
		
	RetriveAllKeyValue map=new RetriveAllKeyValue();
	
		
		map.addElement(131, "Amit");
		map.addElement(132, "sumit");
		map.addElement(133, "karan");
		map.addElement(134, "ranjeet");
		map.addElement(125, "arif");
		
		map.printAllKeyValue();
			
	}


}
