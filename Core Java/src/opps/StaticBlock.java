package opps;

public class StaticBlock {

	static int x=20;
	static int y;
	static int total;
	
	static {
	
		System.out.println("This is first Static Block");
		y=x*2;
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
