package opps;

public class InnerClass {

	class College{

		private String name_of_College;
		
		College(String name_of_College){
			this.name_of_College=name_of_College;
		}
		
		class Student{
			
			private String name;
			private int age;
			
			Student(String name,int age){
				this.name=name;
				this.age=age;
			}
			
			public void displayInfo() {
				System.out.println("Student Name :"+name);
				System.out.println("Student age :"+age);
				System.out.println("College Name :"+name_of_College);
			}
			
		}
		
	}
	
	public static void main(String[] args) {
	
		InnerClass innerClass=new InnerClass();
		InnerClass.College college= innerClass.new College("NIT Raipur");
		
		InnerClass.College.Student student= college.new Student("Krishna",22);
	
		student.displayInfo();
	}
}
