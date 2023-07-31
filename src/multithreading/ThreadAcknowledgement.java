package multithreading;

import java.util.Scanner;

public class ThreadAcknowledgement {



	static class ThreadOne extends Thread{
	
	public void run() {
		Scanner scan=new Scanner(System.in);
		System.out.println("\n\t Reading from Thread One(1) .");
		System.out.println("Enter The Text here :");
		String str=scan.next();
		
		System.out.print("Entere Text is "+str);
	}
}

	static class ThreadTwo extends Thread{
		
	public void run() {
		Scanner scan=new Scanner(System.in);
		System.out.println("\n\t Reading from Thread Two (2) .");
		System.out.println("Enter The Text here :");
		String str=scan.next();
		
		System.out.print("Entere Text is "+str);
	}
}

	static class ThreadThird extends Thread{
		
	public void run() {
		Scanner scan=new Scanner(System.in);
		System.out.println("\n\t Reading from Thread Third(3).");
		System.out.println("Enter The Text here :");
		String str=scan.next();
		
		System.out.print("Entere Text is "+str);
	}
}


	public static void main(String[] args) {
		
		
		ThreadOne one=new ThreadOne();
		ThreadTwo two=new ThreadTwo();
		ThreadThird three=new ThreadThird();
		
		
			one.start();
			two.start();
			three.start();
		
	}
}
