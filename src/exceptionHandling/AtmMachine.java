package exceptionHandling;
import java.util.Scanner;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class AtmMachine {
    public static void main(String[] args) {
        try {
            double accountBalance = getAccountBalance();
            double withdrawalAmount = getWithdrawalAmount();

            if (withdrawalAmount > accountBalance) {
                throw new InvalidInputException("Insufficient account balance.");
            }

            double remainingBalance = accountBalance - withdrawalAmount;
            System.out.println("Remaining account balance: " + remainingBalance);
        } catch (InvalidInputException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static double getAccountBalance() throws InvalidInputException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your account balance: ");
        String input = scanner.nextLine();

        try {
            double accountBalance = Double.parseDouble(input);
            if (accountBalance < 0) {
                throw new InvalidInputException("Account balance cannot be negative.");
            }
            return accountBalance;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid account balance. Please enter a numeric value.");
        }
    }

    private static double getWithdrawalAmount() throws InvalidInputException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount you want to withdraw: ");
        String input = scanner.nextLine();

        try {
            double withdrawalAmount = Double.parseDouble(input);
            if (withdrawalAmount < 0) {
                throw new InvalidInputException("Withdrawal amount cannot be negative.");
            }
            return withdrawalAmount;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid withdrawal amount. Please enter a numeric value.");
        }
    }
}

