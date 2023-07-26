package exceptionHandling;

import java.util.Scanner;

class InvalidDimensionException extends Exception{
	
	public InvalidDimensionException(String msg) {
		super(msg);
	}
}

public class AreaOfRectangle {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		try {
			System.out.print("Please Enter the length of Recatangle :");
			double length=scan.nextDouble();
			
			System.out.print("Please Enter the width of Rectangle :");
			double width=scan.nextDouble();
		
		
			double aeraOfRectangle= calculateArea(length, width);
			System.out.println("Area Of Rectangle :"+aeraOfRectangle);
		} catch (InvalidDimensionException e) {
			System.out.println("Error :"+ e.getMessage());
		}
		
	}
	
	private static double calculateArea(double length,double width) throws InvalidDimensionException {
		
		try {
		if(length<=0 || width <=0) {
			throw new InvalidDimensionException("Please Enter Positive Dimension");
		}
		return length*width;
		}catch(InvalidDimensionException e) {
			throw new InvalidDimensionException("Please Enter Numeric value");
		}
	}
}
