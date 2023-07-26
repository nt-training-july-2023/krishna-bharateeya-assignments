package exceptionHandling;

import java.util.ArrayList;
import java.util.Scanner;

public class ListOfString {

	public static void main(String[] args) {
		
		ArrayList<String> listofString=new ArrayList<>();
		
		listofString.add("Cat");
		listofString.add("Dog");
		listofString.add("Rat");
		listofString.add("parrot");
		listofString.add("Cow");
		for(int i=0;i<listofString.size();i++) {
			System.out.print(listofString.get(i)+"\t");
		}
		
		System.out.println();
		Scanner scan=new Scanner(System.in);
		try {
			System.out.println("Enter the index to show the specified Element :");
			int index=scan.nextInt();
			
			String element= getElementAtIndex(listofString,index);
			
			System.out.println("Element At Index "+index+":"+element);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invilid Index ! the range of Index is 0 to "+(listofString.size()-1)+" .");
			
		} catch (NullPointerException e) {
            System.out.println("The list is null! Please initialize the list with elements.");

		} catch (Exception e) {
			System.out.println("An unexcepted problem occured.\n"+e.getMessage());
		}
		
	}
	
	public static String getElementAtIndex(ArrayList<String>listofString,int index ) {
		if(listofString==null) {
			throw new NullPointerException();
		}
		if(index<0 || index> listofString.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		return listofString.get(index);
	}
}
