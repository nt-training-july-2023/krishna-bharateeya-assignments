package opps;



class Pi{
	float pi=2.14F;
}

public class Final {

	public static void main(String[] args) {
		
		final Pi p=new Pi();
		p.pi=3.13F;
		
		System.out.println("PI : "+p.pi);
		
		final int a=20;
		//a=30;error
	}
}
