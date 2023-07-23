package Programs;

import java.util.Scanner;

public class Program23_StringOperation {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println("please enter a sentense :");
		String str=scan.nextLine();
		
		System.out.println("Length Of String is : "+str.length());
		
		System.out.println("please enter a another sentense :");
		String str1=scan.nextLine();
		
		String str3=str.concat(str1);
		
		System.out.println("after concating the both string :\n"+str3);
		
		System.out.println("Enter the index to get the letter :");
		int idx=scan.nextInt();
		
		System.out.println("The Letter on this index is :"+ str3.charAt(idx));
		
		System.out.println("Enter the text check start with :");
		String s=scan.next();
		System.out.println("Result : "+str3.startsWith(s));
		
		System.out.println("Enter the charactor to find its postion :");
		char c = scan.next().charAt(0);
		System.out.println("index of this letter :"+ str3.indexOf(c));
		
		
		System.out.println("Enter the index to replae :");
		int index=scan.nextInt();
		
		System.out.println("Enter the letter to replae with :");
		char ch = scan.next().charAt(0);
		
		str = str.substring(0, index) + ch+ str.substring(index + 1);
		System.out.println("Updated String is :"+str);
		
		
		String st1="hello";
		String st2="hello";
		String st3=new String("hello");
		System.out.println("String 1 :"+st1+"\nString 2 :"+st2+"\nString 3 :"+st3);
		System.out.println("Check Equlity with '==' :"+(st1==st2));
		System.out.println("Check Equlity with '==' :"+(st1==st3));
		System.out.println("Check Equlity with '.equal' :"+(st1.equals(st3)));
		
	}
}
