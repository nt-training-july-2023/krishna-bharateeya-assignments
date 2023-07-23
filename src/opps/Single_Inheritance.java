package opps;

class Device{
	
	void turnOn() {
		System.out.println("Device turn On");
	}
	
	void turnOff() {
		System.out.println("Device turn Off");
	}
}

class HeadPhone extends Device{
	
	
	void turnOn() {
		System.out.println("HeadPhone switched on.");
	}
	
	void turnOff() {
		System.out.println("HeadPhone switched off");
	}
	
   void playMusic() {
        System.out.println("Headphone is playing music.");
    }
	
	
}
public class Single_Inheritance {

	public static void main(String[] args) {
		
		HeadPhone headPhone=new HeadPhone();
		headPhone.turnOn();
		headPhone.turnOff();
		headPhone.playMusic();
		
	}
}
