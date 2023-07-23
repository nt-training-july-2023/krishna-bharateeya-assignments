package opps;


class calculator{
	
	int add(int a,int b) {
		
		return a+b;
	}
	
	String add(String str1,String str2) {
		
		return str1+str2;
	}
	
	float add(float a,float b) {
		return a+b;
	}
}

public class Polymorphism_RunTime {

	public static void main(String[] args) {
		calculator cal=new calculator();
		
		System.out.println("Addtion of number : "+cal.add(34, 6));
		
		System.out.println("Addtion of String : "+cal.add("kri", "shna"));
		
		System.out.println("Addtion of Float Value: "+cal.add(4.9F, 6.27F));
	}
}
