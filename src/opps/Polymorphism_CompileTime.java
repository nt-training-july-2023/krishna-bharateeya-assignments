package opps;

class Bird{
	
	void makeSound() {
		System.out.println("Some generic Bird sound");
	}
}

class Parrot extends Bird{
	
	void makeSound() {
		System.out.println("parrot speaking");
	}
}

class PeaCock extends Bird{
	
	void makeSound() {
		System.out.println("Peacock Speaking");
	}
}
class Peagon extends Bird{
	
	void makeSound() {
		System.out.println("peagon Speaking");
	}
}


public class Polymorphism_CompileTime {

	public static void main(String[] args) {
		Bird parrot=new Parrot();
		Bird peacock=new PeaCock();
		Bird Peagon=new Peagon();
		
		parrot.makeSound();
		peacock.makeSound();
		Peagon.makeSound();
				
	}
}
