package opps;

	public class FinalBlank {

	    final int a;

	    static final int b;

	    final double c;

	    final String d = "Hello";

	    final char e;

	    public FinalBlank() {
	        a = 10;
			this.e = 0;
	    }

	    static {
	        b = 20;
	    }

	    {
	        c = 3.14;
	    }

	    public FinalBlank(char value) {
	        this.a = 0;
			e = value;
	    }

	    public static void main(String[] args) {
	        FinalBlank obj1 = new FinalBlank();
	        FinalBlank obj2 = new FinalBlank('X');

	        System.out.println("a: " + obj1.a);
	        System.out.println("b: " + b);
	        System.out.println("c: " + obj1.c);
	        System.out.println("d: " + obj1.d);
	        System.out.println("e: " + obj2.e);
	    }
	}


