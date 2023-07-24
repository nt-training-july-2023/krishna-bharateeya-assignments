package opps;

public class Anonymous_Class {

	public void organizeCollegeEvent() {
        CollegeEvent event = new CollegeEvent() {
            @Override
            public void displayEventDetails() {
                System.out.println("An amazing College event is happening!");
            }
        };

        event.displayEventDetails();
    }
	
	public static void main(String[] args) {
		
		Anonymous_Class college=new Anonymous_Class();
		college.organizeCollegeEvent();
	}
}
interface CollegeEvent {
    void displayEventDetails();
}