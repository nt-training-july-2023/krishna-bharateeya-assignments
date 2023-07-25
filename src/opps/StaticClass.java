package opps;

public class StaticClass {

	static int x=20;
	static int y;
	int total;
	
	static {
	
		System.out.println("This is first Static Block");
		y=x*2;
	}
	
	static {
		System.out.println("This is Second static Block");
		//total=x+y;//error
	}
	
	public static void dispaly() {
		//System.out.println("Total : "+total);//error
	}
	
	public static void main(String[] args) {
		dispaly();
	}

}
