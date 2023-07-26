package exceptionHandling;
import java.util.Scanner;

class InvilidInputException extends Exception{
	
	public InvilidInputException(String msg) {
		super(msg);
	}
}

public class Atm{
	
	public static void main(String[] args){

		try {
			double accountBalance=getAccountBalance();
			double withDrawAmount=getWithdrawAmount();
			
			if(withDrawAmount>accountBalance) {
				throw new InvilidInputException("Insufficiant Amount in your Account");
			}
			
			double remaingBalence=accountBalance-withDrawAmount;
			System.out.println("Your Remaining Account Balance Is : "+remaingBalence);
		} catch (InvilidInputException e) {
			System.out.println(""+e.getMessage());
		}
		
	}
	
	private static double getAccountBalance() throws InvilidInputException{
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter your account balance :");
		
		double accountBalance=scan.nextDouble();
		
		try {
		
			if(accountBalance<0) {
				throw new InvilidInputException("Account Balance Cannot be Negative");
			}
			return accountBalance;
		} catch (InvilidInputException e) {
			throw new InvilidInputException("Invilid Account balance !! Please Enter the Numeric value");
		}
	  }
	
	
	private static double getWithdrawAmount()throws InvilidInputException{
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Withdraw amount :");
		double withdrawAmount=scan.nextDouble();
		try {
		if(withdrawAmount<0) {
			throw new InvilidInputException("Withdraw Amount cannot be negative .");
		}
		return withdrawAmount;
		}catch(InvilidInputException e) {
			throw new InvilidInputException("Please Enter Numeric Value");
		}
	}

}