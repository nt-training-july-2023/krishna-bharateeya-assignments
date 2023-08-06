package multithreading;

import java.util.Scanner;

public class ManyTask {
	public static int num=0;

	static class SumOfNumbers extends Thread{
	
	public void run() {
		int sum=0;
		for(int i=1;i<=num;i++) {
			sum=sum+i;
		}
		System.out.print("Sum Of "+num+" : "+sum);
	}
}

	static class Reverse extends Thread{
	
	public void run() {
		
		System.out.println("The reverse List is :");
		for(int i=num;i>0;i--) {
			System.out.print(i+" ");
		}
	}
}


	static class Fibonacci extends Thread {
	    public void run() {
	        int prev = 0;
	        int current = 1;
	        int sum;

	        System.out.println("\nFibonacci series up to " + num + " is:");
	        System.out.print(prev + " " + current + " ");

	        for (int i = 2; i < num; i++) {
	            sum = prev + current;
	            System.out.print(sum + " ");
	            prev = current;
	            current = sum;
	        }
	    }
	}



	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.print("Enter the number :");
		num=scan.nextInt();
		
		SumOfNumbers sum=new SumOfNumbers();
		Reverse reverse=new Reverse();
		Fibonacci fib=new Fibonacci();
		
		sum.start();
		reverse.start();
		fib.start();
		
	}
}
