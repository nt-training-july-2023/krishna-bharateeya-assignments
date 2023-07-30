package multithreading;

class Thread1 extends Thread{
	
	public void run() {
		int i=0;
		while(true) {
			System.out.println(i);
			i++;
			System.out.println("Running from thread");
		}
	}
}

public class ThreadTest {

	public static void main(String[] args) {
		
		Thread1 thread1=new Thread1();
		thread1.start();
		
		int i=0;
		while(true) {
			System.out.println(i);
			i++;
			System.out.println("Running from main");
		}
	}
}
