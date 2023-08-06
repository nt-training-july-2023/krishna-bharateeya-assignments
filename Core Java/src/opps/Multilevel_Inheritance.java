package opps;

class Vehicale{
	
	void start() {
		System.out.println("Vahicle Started.");
	}
	void drive() {
		System.out.println("Driving....");
	}
	void Stop() {
		System.out.println("Vahicle Stoped.");
	}
}


class Car extends Vehicale{
	
	void start() {
		System.out.println("car Started.");
	}
	void drive() {
		System.out.println("Driving car....");
	}
	void Stop() {
		System.out.println("car Stoped.");
	}
	
	void palyMusic() {
		System.out.println("Playing Music....");
	}
}

class SportCar extends Car{
	
	void start() {
		System.out.println("Sport car Started.");
	}
	void drive() {
		System.out.println("Sport car Driving ....");
	}
	void Stop() {
		System.out.println("Sport car Stoped.");
	}
	
	void palyMusic() {
		System.out.println("Sport Playing Music....");
	}
	
	void driveFast() {
		System.out.println("Driving very Fast....");
	}
	
}
public class Multilevel_Inheritance {
	
	public static void main(String[] args) {
		SportCar car=new SportCar();
		
		car.start();
		car.drive();
		car.palyMusic();
		car.driveFast();
		car.Stop();
	}

}
