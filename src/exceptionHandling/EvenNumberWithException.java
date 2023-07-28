package exceptionHandling;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class NotEvenNumberException extends Exception {

    NotEvenNumberException(String message) {
        super(message);
    }
}

public class EvenNumberWithException {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        try {
            System.out.print("Enter number to check even or not: ");
            int num = scan.nextInt();

            checkEvenNumber(num);
            System.out.println("This is an even Number");

        }catch(NotEvenNumberException e) {
        	System.out.println("This is not Even number."+e.getMessage());
        }catch(InputMismatchException e) {
        	System.out.println("Please Enter Numeric value."+e.getMessage());
        }catch (Exception e) {
        
            System.out.println("An Unexcepted problem occured." +e.getMessage());
        }
    }

    private static void checkEvenNumber(int num) throws NotEvenNumberException {
        if (num%2 != 0) {
            throw new NotEvenNumberException("Please Enter an Even number.");
        }
        
    }
}