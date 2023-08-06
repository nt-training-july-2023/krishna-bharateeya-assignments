package opps;

public class Static_Nasted_Class {

	static class Professor {
        private String name;
        private String subject;

        public Professor(String name, String subject) {
            this.name = name;
            this.subject = subject;
        }

        public void displayInfo() {
            System.out.println("Teacher Name: " + name + ", Subject: " + subject);
        }
    }
	
	public static void main(String[] args) {
	
		Professor professor1 = new Professor("Mr. Dibakar Saha", "Data Science");
		Professor professor2= new Professor("Mrs. Manju Panday", "Cryptography");
		
		professor1.displayInfo();
		professor2.displayInfo();
	}
}
