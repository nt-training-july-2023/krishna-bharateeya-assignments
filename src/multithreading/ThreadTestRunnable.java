package multithreading;

class RunnableThread implements Runnable{
	
	public void run() {
		int i=0;
		while(true) {
			System.out.println(i);
			i++;
			System.out.println("Running from thread");
		}
	}
}

public class ThreadTestRunnable {

	public static void main(String[] args) {
		
		RunnableThread runnableThread=new RunnableThread();
		Thread thread=new Thread(runnableThread);

		thread.start();
		
		int i=0;
		while(true) {
			System.out.println(i);
			i++;
			System.out.println("Running from main");
		}
	}
}
