package Programs;
import java.util.*;

public class Program7_QuardaticEquation {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter value of a :");
		float a=scan.nextFloat();
		
		System.out.println("Enter the value of b :");
		float b=scan.nextFloat();
		
		System.out.println("Enter the value of c :");
		float c=scan.nextFloat();
		
		float temp= (b*b)-4*a*c;
		
		if(temp>0) {
			double r1= -b+ Math.sqrt(temp)/2*a;
			double r2= b+Math.sqrt(temp)/2*a;
			System.out.println("The real Root are : "+r1+" , "+r2);
			System.out.println();
		}else if(temp==0) {
			float r1= -b/(2*a);
			float r2= (b/2*a);
			System.out.println("The equal Root are : "+ r1+" ,"+r2);
			System.out.println();
		}else {
			System.out.println("Roots Are Imaginary");
		}
		

	}

}
