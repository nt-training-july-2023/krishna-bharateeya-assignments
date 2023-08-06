package basics_Of_Java;
import java.util.*;

public class Program5_ArithmaticOperator {
	
	public static void main(String args[]) {
		
		 Scanner scan= new Scanner(System.in);
		 
		 System.out.println("Enter the First number :");
		 int num1=scan.nextInt();
		 
		 System.out.println("Enter the second number :");
		 int num2=scan.nextInt();
		 
		 System.out.println("\n");
		 
		 System.out.println("Addition : "+ (num1+num2));
		 System.out.println("Subtraction :"+ (num1-num2));
		 System.out.println("multiplecation :"+(num1*num2));
		 System.out.println("Divition :"+(num1/num2));
		
	}

}
