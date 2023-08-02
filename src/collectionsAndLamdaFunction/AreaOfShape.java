package collectionsAndLamdaFunction;



interface Shape {
    double area();
}

public class AreaOfShape {
	
	 static class Rectangle implements Shape {
	        private double length;
	        private double width;

	        public Rectangle(double length, double width) {
	            this.length = length;
	            this.width = width;
	        }

	        @Override
	        public double area() {
	            return length * width;
	        }
	    }

	    static class Square implements Shape {
	        private double side;

	        public Square(double side) {
	            this.side = side;
	        }

	        @Override
	        public double area() {
	            return side * side;
	        }
	    }

	    static class Circle implements Shape {
	        private double radius;

	        public Circle(double radius) {
	            this.radius = radius;
	        }

	        @Override
	        public double area() {
	            return Math.PI * radius * radius;
	        }
	    }

	    static class Cube implements Shape {
	        private double side;

	        public Cube(double side) {
	            this.side = side;
	        }

	        @Override
	        public double area() {
	            return 6 * side * side;
	        }
	    }

	    static class Sphere implements Shape {
	        private double radius;

	        public Sphere(double radius) {
	            this.radius = radius;
	        }

	        @Override
	        public double area() {
	            return 4 * Math.PI * radius * radius;
	        }
	    }

	    static class Cylinder implements Shape {
	        private double radius;
	        private double height;

	        public Cylinder(double radius, double height) {
	            this.radius = radius;
	            this.height = height;
	        }

	        @Override
	        public double area() {
	            return 2 * Math.PI * radius * (radius + height);
	        }
	    }

	
	
    public static void main(String[] args) {

        Shape rectangle = new Rectangle(5, 8);
        Shape square = new Square(6);
        Shape circle = new Circle(4);
        Shape cube = new Cube(3);
        Shape sphere = new Sphere(2);
        Shape cylinder = new Cylinder(3, 5);

       
        System.out.println("Rectangle Area: " + rectangle.area());
        System.out.println("Square Area: " + square.area());
        System.out.println("Circle Area: " + circle.area());
        System.out.println("Cube Area: " + cube.area());
        System.out.println("Sphere Area: " + sphere.area());
        System.out.println("Cylinder Area: " + cylinder.area());
    }

   }
