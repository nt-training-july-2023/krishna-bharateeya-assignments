package exceptionHandling;
import java.util.Scanner;

class InvalidInputException extends Exception{
	
	public InvalidInputException(String msg) {
		super(msg);
	}
}

public class AtmMachine{
	
	public static void main(String[] args){

		try {
			double accountBalance=getAccountBalance();
			double withDrawAmount=getWithdrawAmount();
			
			if(withDrawAmount>accountBalance) {
				throw new InvalidInputException("Insufficiant Amount in your Account");
			}
			
			double remaingBalence=accountBalance-withDrawAmount;
			System.out.println("Your Remaining Account Balance Is : "+remaingBalence);
		} catch (InvalidInputException e) {
			System.out.println("Error :"+e.getMessage());
		}
		
	}
	
	private static double getAccountBalance() throws InvalidInputException{
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter your account balance :");
		
		double accountBalance=scan.nextDouble();
		
		try {
		
			if(accountBalance<0) {
				throw new InvalidInputException("Account Balance Cannot be Negative");
			}
			return accountBalance;
		} catch (InvalidInputException e) {
			throw new InvalidInputException("Invilid Account balance !! Please Enter the Numeric value");
		}
	  }
	
	
	private static double getWithdrawAmount()throws InvalidInputException{
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Withdraw amount :");
		double withdrawAmount=scan.nextDouble();
		try {
		if(withdrawAmount<0) {
			throw new InvalidInputException("Withdraw Amount cannot be negative .");
		}
		return withdrawAmount;
		}catch(InvalidInputException e) {
			throw new InvalidInputException("Please Enter Numeric Value");
		}
	}

}