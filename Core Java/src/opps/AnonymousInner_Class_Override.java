package opps;


interface Task {
    void execute();
}

public class AnonymousInner_Class_Override {
	
    public static void main(String[] args) {
        Task task1 = new Task() {
            public void execute() {
                System.out.println("Task 1 is executing...");
            }
        };

        task1.execute(); 
        
        
        Task task2 = new Task() {
            
        	public void execute() {
                System.out.println("Task 2 is executing...");
            }
        };

        task2.execute(); 
        
        
        
        Task task3 = new Task() {
        
            public void execute() {
                System.out.println("Task 3 is executing...");
            }
        };

        task3.execute(); 
    }
}
