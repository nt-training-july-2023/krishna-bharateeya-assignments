package test;

public class PrintTest {

	public static void main(String[] args) {
		
		PrintStatement p =(String str1,String str2,String str3)->{
				 
				System.out.println("String 1 :"+str1);
				System.out.println("String 1 :"+str2);
				System.out.println("String 1 :"+str2);
			
		};
		
		p.print("Abc", "cde", "efg");
		
	}
}
