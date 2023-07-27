package exceptionHandling;

import java.util.Scanner;

class InvalidPasswordException extends Exception{
	
	InvalidPasswordException(String message){
		super(message);
	}
}

public class PasswordValidator {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		try {
			System.out.println("Enter Your Password Here :");
			String password=scan.next();
			
			checkPassword(password);
			
			System.out.println("This is valid pssword.");
			
			
		} catch (InvalidPasswordException e) {
			System.out.println("This is not a valid password.");
		}
	}
	
	private static void checkPassword(String password) throws InvalidPasswordException {
		
		if(password.length()<8) {
			throw new InvalidPasswordException("Enter atleast 8 charactor.");
		}
		
		 boolean letter = false;
	     boolean number = false;
		
	     for(char c :password.toCharArray()) {
	    	 if(Character.isLetter(c)) {
	    		 letter=true;
	    	 }
	    	 
	    	 if(Character.isDigit(c)) {
	    		 number=true;
	    	 }
	     }
	     
	     if(!letter|| !number) {
	    	 throw new InvalidPasswordException("Password must contain both letters and numbers.");
	     }
	}
	
}
