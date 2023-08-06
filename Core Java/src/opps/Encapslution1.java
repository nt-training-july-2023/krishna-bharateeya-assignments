package opps;

class Employee {

	private String empName;
	private int salary;
	private int bonus;
	
	
	public void setEmpName(String empName){
		
		this.empName=empName;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setSalary(int salary) {
		this.salary=salary;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setBonus(int bonus) {
		
		if(salary >10000)
			this.bonus=0;
		else
			this.bonus=bonus;
	}
	public int getBonus() {
		return bonus;
	}
		
}
public class Encapslution1{
public static void main(String args[]) {
	
	Employee ecp=new Employee();
	
	ecp.setEmpName("Krishna Kumar");
	ecp.setSalary(12000);
	ecp.setBonus(2000);
	
	
	Employee ecp1=new Employee();
	
	ecp1.setEmpName("Praveen");
	ecp1.setSalary(8000);
	ecp1.setBonus(5000);
	
	System.out.println("Details of "+ecp.getEmpName());
	System.out.println("Name :"+ecp.getEmpName());
	System.out.println("Salary :"+ecp.getSalary());
	System.out.println("Bonus :"+ecp.getBonus());
	
	System.out.println("\n");
	System.out.println("Details of "+ecp1.getEmpName());
	System.out.println("Name :"+ecp1.getEmpName());
	System.out.println("Salary :"+ecp1.getSalary());
	System.out.println("Bonus :"+ecp1.getBonus());
	
}
}