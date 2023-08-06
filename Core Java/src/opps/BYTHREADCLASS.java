package opps;

public class BYTHREADCLASS extends Thread{

	public void run(){
		for (int i=0;i<3;i++)
		System.out.print(i+" ");
		
	}
	
	public static void main(String args[]){
		BYTHREADCLASS t1=new BYTHREADCLASS(); BYTHREADCLASS t2=new BYTHREADCLASS();
	
		t1.start();
		t2.start();
	}
	
}
