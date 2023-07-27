package exceptionHandling;

import java.io.IOException;
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

        } catch (Exception e) {
            System.out.println("Invalid Input: " + e.getMessage());
        }
    }

    private static void checkEvenNumber(int num) throws NotEvenNumberException {
        if (num%2 != 0) {
            throw new NotEvenNumberException("Please Enter an Even number.");
        }
    }
}
