package collectionsAndLamdaFunction;

import java.util.HashMap;
import java.util.Map;

public class MapCrud {
	public static void main(String[] args) {
		
		Map<Integer,String> map=new HashMap<>();
		map.put(1, "Dog");
		map.put(2, "Cat");
		map.put(3, "Rat");
		map.put(4, "Frog");
		map.put(5, "Cock");
		
		System.out.println("Map Elements :"+map);
		
		System.out.println("Value for key "+2+" :"+map.get(2));
		
		map.put(2, "updated cat");
		System.out.println("Value for key "+2+" :"+map.get(2));
		
		map.remove(2);
		System.out.println("Value for key "+2+" :"+map.get(2));
		
		
	}
	
	
	

}
