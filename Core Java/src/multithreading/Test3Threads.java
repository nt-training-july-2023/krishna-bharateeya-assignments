package multithreading;

class UsingRunnableThread1 implements Runnable{
	
	public void run() {
		int i=0;
		while(i<100) {
			System.out.println(i);
			i++;
			System.out.println("Running from Using Runnable Thread 1.");
		}
	}
}

class UsingRunnableThread2 implements Runnable{
	
	public void run() {
		int i=0;
		while(i<100) {
			System.out.println(i);
			i++;
			System.out.println("Running from Using Runnable Thread 2.");
		}
	}
}

class UsingThread extends Thread{
	
	public void run() {
		int i=0;
		while(i<100) {
			System.out.println(i);
			i++;
			System.out.println("Running from thread");
		}
	}
}

public class Test3Threads {

	public static void main(String[] args) {
		
		
		UsingThread thread1=new UsingThread();
		thread1.start();
		
		UsingRunnableThread1 runThed1=new UsingRunnableThread1();
		UsingRunnableThread2 runThed2=new UsingRunnableThread2();
		
		Thread thread2=new Thread(runThed1);
		Thread thread3=new Thread(runThed2);
		
		thread2.start();
		thread3.start();

	}

}
