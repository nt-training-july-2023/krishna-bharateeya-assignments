package multithreading;

class Bank{
	
	synchronized public void checkBalence(String name) {
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println(name+" Your Account Balence XXX890.");
	}
	
	synchronized public void withDraw(String name,int amount) {
		System.out.println("Whthdrawing the amount...");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println("Amount withdrawed :"+amount);
	}
	
}

class Customer extends Thread{
	String name;
	int amount;
	Bank bank;
	
	Customer(String n,Bank b,int amt) {
	
		name=n;
		bank=b;
		amount=amt;
	}
	
	
	public void useBank() {
		bank.checkBalence(name);
		bank.withDraw(name, amount);
	}
	
	public void run() {
		useBank();
	}
	
}

public class BankingApplication {

	public static void main(String[] args) {
		Bank bank=new Bank();
		Customer c1=new Customer("Krishna", bank, 1200);
		Customer c2=new Customer("Praveen", bank, 5200);
		c1.start();
		c2.start();
	}
}
