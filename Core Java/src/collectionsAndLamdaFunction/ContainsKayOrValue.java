package collectionsAndLamdaFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ContainsKayOrValue {

    private Map<Integer, String> map = new HashMap<>();

    public void addElement(int key, String value) {
        map.put(key, value);
    }

    public void checkElement(String str) {

        int flag = 0;      
        if(str.charAt(0)>=64 && str.charAt(0)<=90 || str.charAt(0)>=97 && str.charAt(0)<=122) {
        	
        	 for (Entry<Integer, String> entry : map.entrySet()) {
                 if (entry.getValue().contains(str)) {
                     flag = 1;
                     System.out.println("Found Value: " + entry.getValue()+" with Key :"+entry.getKey());
                 }
                
             }
        }else {
        	
        	 int searchValue;
             try {
                 searchValue = Integer.parseInt(str);
             } catch (NumberFormatException e) {
                 System.out.println("Error: The input string is not a valid integer.");
                 return;
             }
             
        	for (Entry<Integer, String> entry : map.entrySet()) {
        		
        		if (entry.getKey() == searchValue) {
        			flag = 1;
        			System.out.println("Found Key: " + entry.getKey()+" with Value :"+entry.getValue());
        		}
        	}
        }


        if (flag == 0)
            System.out.println("Given String not found in the map.");
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ContainsKayOrValue map = new ContainsKayOrValue();

        map.addElement(101, "Anil");
        map.addElement(102, "Sunil");
        map.addElement(103, "Krishna");

        System.out.println("Enter the string to search in the map:");
        String str = scan.next();

        map.checkElement(str);

        scan.close();
    }
}
