package collectionsAndLamdaFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ThresholdSize {
	
	private Map<Integer,String> map=new HashMap<>();
	
	private int thresholdSize;
	
	private ThresholdSize(int thresholdSize) {

		this.thresholdSize=thresholdSize;
	}
	
	public void addElement(int key,String value) {
		map.put(key, value);
	}
	
	public void printCheckAndClearIfThresholdSize() {
		System.out.println("List of Elements :");
		
		for(Entry<Integer, String> entry : map.entrySet()) {
			System.out.println("Key :"+entry.getKey()+", Value :"+entry.getValue());
		}
		if(map.size()>=thresholdSize) {
			System.out.println("Map size is Greater than or Equal to "+thresholdSize);
			map.clear();
		}
	}
	
	public static void main(String[] args) {
		
		
		ThresholdSize map=new ThresholdSize(4);
		map.addElement(121, "Amit");
		map.addElement(122, "sumit");
		map.addElement(123, "karan");
		
		map.printCheckAndClearIfThresholdSize();
		map.addElement(124, "ranjeet");
		map.printCheckAndClearIfThresholdSize();
//		map.addElement(125, "arif");
		map.printCheckAndClearIfThresholdSize();
		
		
		
		
		
	}

}
