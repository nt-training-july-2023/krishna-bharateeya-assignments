package exceptionHandling;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayOfInteger {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		ArrayList<Integer> arrList=new ArrayList<Integer>();
		
		arrList.add(10);
		arrList.add(20);
		arrList.add(30);
		arrList.add(40);
		arrList.add(50);
		arrList.add(60);
		arrList.add(70);
		arrList.add(80);
		arrList.add(90);
		
		for(int i=0;i<arrList.size();i++) {
			System.out.print(arrList.get(i)+"\t");
		}
		
		try {
			System.out.println("\nEnter the Index to find the Element :");
			int index=scan.nextInt();
			
			int element= getElementOnIndex(arrList,index);
			
			System.out.println("Element On index "+index+" :"+element);
		}catch(NullPointerException e){
			System.out.println("Please Enter the index between 0 to "+(arrList.size()-1)+"\n"+e.getMessage());
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("The Maximum length of List is "+(arrList.size()-1)+". Enter in Range!!\n"+e.getMessage());
		}catch (Exception e) {
			System.out.println("An Unexcepted Problem occured.\n"+e.getMessage());
		}
		
	}
	
	public static int getElementOnIndex(ArrayList<Integer> arrList,int index) {
		
		if(index<0) {
			throw new NullPointerException();
		}
		
		if(index>arrList.size()-1) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		return arrList.get(index);
	}
}
