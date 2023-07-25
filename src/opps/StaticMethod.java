package opps;

public class StaticMethod {

	static int x=10;
	static int y;
	static int total;
	
	static {
	
		System.out.println("This is first Static Block");
		y=x*7;
		System.out.println("Value Y :"+y);
	}
	
	static {
		System.out.println("This is Second static Block");
		total=x+y;
	}
	
	public static void dispaly() {
		System.out.println("Total : "+total);
	}
	
	public static void main(String[] args) {
		dispaly();
	}

}
