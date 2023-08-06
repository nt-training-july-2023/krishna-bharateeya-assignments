package exceptionHandling;

/**
 * @author Krishna Bharateeya
 * This program calculates the area of different shapes - Rectangle, Circle, and Triangle.
 */

public class Area_Calculator {
	
	 /**
     * Calculating the area of a rectangle.
     *
     * @param length The length of the rectangle.
     * @param width  The width of the rectangle.
     * @return The area of the rectangle.
     */
	
	public static double rectangelArea(double length,double width) {
		
		return length*width;
	}
	
	
    /**
     * Calculating the area of a circle.
     *
     * @param radius The radius of the circle.
     * @return The area of the circle.
     */
	
	public static double circleArea(double redius) {
		
		return Math.PI* Math.pow(redius, 2);
	}
	
	
	 /**
     * Calculating the area of a triangle.
     *
     * @param base   The base length of the triangle.
     * @param height The height of the triangle.
     * @return The area of the triangle.
     */
	
	public static double trangleArea(double base , double height) {
		
		return 0.5*base*height;
	}
	
	public static void main(String[] args) {
		//calling the methods
		double rectangleArea= rectangelArea(20.0, 4.8);
		System.out.println("Area Of rectangle : "+rectangleArea);
		
		double circleArea= circleArea(5.8);
		System.out.println("Area Of circle : "+circleArea);
		
		double trangleArea= trangleArea(20.0, 30.8);
		System.out.println("Area Of triangle : "+trangleArea);
	}

}
