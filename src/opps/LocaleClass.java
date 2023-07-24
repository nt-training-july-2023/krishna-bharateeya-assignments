package opps;

public class LocaleClass {

	public void createClassroom(String className) {
        class Classroom {
            private String name;

            public Classroom(String name) {
                this.name = name;
            }

            public void displayInfo() {
                System.out.println("Classroom Name: " + name);
            }
        }

        Classroom classroom = new Classroom(className);
        classroom.displayInfo();
    }
	public static void main(String[] args) {
		
		LocaleClass college= new LocaleClass();
		college.createClassroom("Class MCA 1st");
	}
}
