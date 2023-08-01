package multithreading;

class MyThread1 implements Runnable{
	
	public void run() {
		int i=0;
		while(i<10) {
			
			System.out.println("Running from Using Runnable Thread 1. ->"+i++);
		}
	}
}

class MyThread2 implements Runnable{
	
	public void run() {
		int i=0;
		while(i<10) {
			
			System.out.println("Running from Using Runnable Thread 2. ->"+i++);
		}
	}
}

class MyThread3 extends Thread{
	
	public void run() {
		int i=0;
		while(i<10) {
			System.out.println("Running from thread class 3.->"+i++);
		}
	}
}

public class ThreadPriority {



	public static void main(String[] args) {
		
		
		MyThread3 thread1=new MyThread3();
		
		MyThread1 runThed1=new MyThread1();
		MyThread2 runThed2=new MyThread2();
		
		Thread thread2=new Thread(runThed1);
		Thread thread3=new Thread(runThed2);

		thread1.setPriority(Thread.MAX_PRIORITY-1);
		thread2.setPriority(Thread.MIN_PRIORITY+1);
		thread3.setPriority(Thread.NORM_PRIORITY);
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
