package collectionsAndLamdaFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Employee {

	private Map<Integer,String> employee=new HashMap<>();
	
	public void addEmployee(int id,String name) {
		employee.put(id, name);
	}
	
	public void matchingKey(String str) {
		
		
		for(Map.Entry<Integer, String> entry : employee.entrySet()) {
			
			if(entry.getValue().contains(str)) {
				System.out.println("Employee Id : "+entry.getKey());
			}
		}
		
	}
	
	public static void main(String[] args) {
		Employee employee=new Employee();
		
		employee.addEmployee(121, "krishna kumar");
		employee.addEmployee(122, "Praveen kodawalli");
		employee.addEmployee(123, "B Hemant");
		employee.addEmployee(124, "Eahshan satyam");
		employee.addEmployee(125, "vivek dubey");
		employee.addEmployee(126, "nachiketa mohanti");
		employee.addEmployee(127, "abhay gupta");
		
		String search="gupta";
		System.out.print("IDs for employees with name containing "+search+" : ");
		employee.matchingKey(search);
	}
	
}
