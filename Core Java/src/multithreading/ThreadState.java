package multithreading;

class Thread4 extends Thread{
	
	public Thread4(String name){
		super(name);
	}
	
	public void run() {
		
		int count=1;
		while(count<100) {
			System.out.println(count++);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


public class ThreadState {

	public static void main(String[] args) throws InterruptedException {
		
		Thread4 t=new Thread4("First Thread");
		
		System.out.println("Thread Name :"+t.getName());
		System.out.println("Thread Priority :"+t.getPriority());
		System.out.println("Current Thread State : "+t.getState());
		t.start();
		System.out.println("Current Thread State : "+t.getState());
		t.sleep(2000);
		System.out.println("Current Thread State : "+t.getState());
		t.join();
		System.out.println("Current Thread State : "+t.getState());
		
	}
}
