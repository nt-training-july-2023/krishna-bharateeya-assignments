package opps;

abstract class Vehicle{
	
	String name;
	
	Vehicle(String name){
		this.name=name;
	}
	
	abstract int numberOfSeat();
	
	void info() {
		System.out.println("Vehicle : "+name);
	}
}

class Cars extends Vehicle{
	
	int seats;
	
	Cars(String name,int seats){
		
		super(name);
		this.seats=seats;
	}
	
	int numberOfSeat() {
		return seats;
	}
	void info() {
		System.out.println("Vehicle : "+name);
		System.out.println("Number Of person can sit :"+seats);
	}
}

class Tractor extends Vehicle{
	
	int seats;
	Tractor(String name,int seats){
		
		super(name);
		this.seats=seats;
	}
	int numberOfSeat() {
		return seats;
	}
	void info() {
		System.out.println("Vehicle : "+name);
		System.out.println("Number Of person can sit :"+seats);
	}
}

public class AbstractClass {

	public static void main(String[] args) {
		
		
		Cars car=new Cars("Fortuner ",7);
		Tractor tractor=new Tractor("Mahindra 265", 1);

		car.info();
		tractor.info();
	}

}
