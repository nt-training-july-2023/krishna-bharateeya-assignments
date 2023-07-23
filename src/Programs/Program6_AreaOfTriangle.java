package Programs;
import java.util.*;
public class Program6_AreaOfTriangle {
	
	public static void main(String args[]) {
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter the Base of Triangle :");
		float base=scan.nextFloat();
		
		System.out.println("Enter the Height of Triangle :");
		float height=scan.nextFloat();
		
		float area= (base*height)/2;
		
		System.out.println("Area Of Triangle is :"+area);
	}

}
