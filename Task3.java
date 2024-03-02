import java.util.InputMismatchException;
import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        // Welcome the user
        System.out.println("Welcome to the ATM!");
        System.out.println();

        // Create an instance of the ATM_Machine class
        ATM_Machine atmMachine = new ATM_Machine();

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Flag to control the transaction loop
        boolean continueTransaction = true;

        // Transaction loop
        while (continueTransaction){
            // Display the menu options
            System.out.println("(C)heck balance | (D)eposit | (W)ithdraw | (E)xit ATM");

            // Get the user's selection and convert it to lowercase
            String mainMenuSelection = scanner.nextLine().toLowerCase();

            // Process the user's selection
            switch (mainMenuSelection){
                case "c":
                    // Check balance
                    atmMachine.checkBalance();
                    System.out.println();
                    break;
                case "d":
                    // Deposit
                    System.out.println("Enter amount to deposit: ");
                    try {
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine();  // Consume leftover newline
                        atmMachine.deposit(depositAmount);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a numeric value.");
                        scanner.nextLine();  // Consume the invalid input
                    }
                    System.out.println();
                    break;
                case "w":
                    // Withdraw
                    System.out.println("Enter amount to withdraw: ");
                    try {
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();  // Consume leftover newline
                        atmMachine.withdraw(withdrawAmount);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a numeric value.");
                        scanner.nextLine();  // Consume the invalid input
                    }
                    System.out.println();
                    break;
                case "e":
                    // Exit
                    continueTransaction = false;
                    System.out.println("Thank you for using the ATM. Goodbye");
                    System.out.println();
                    break;
                default:
                    // Invalid input
                    System.out.println("Invalid input! Please choose one of the displayed options");
                    System.out.println();
            }
        }
    }
}

class ATM_Machine {
    // Create an instance of the BankBalance class
    BankBalance bankBalance = new BankBalance();

    // Method to withdraw money
    public void withdraw(double withdrawAmount){
        try {
            // Check if the amount is negative
            if (withdrawAmount < 0) {
                throw new IllegalArgumentException("Withdrawal amount cannot be negative.");
            }

            // Check if there are sufficient funds
            if (withdrawAmount > bankBalance.getBalance()){
                System.out.println("Withdrawal failed: Insufficient funds");
            }
            else{
                // Deduct the withdrawal amount from the balance
                bankBalance.setBalance(bankBalance.getBalance() - withdrawAmount);
                System.out.println("Withdrawal successful");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to deposit money
    public void deposit (double depositAmount){
        try {
            // Check if the amount is negative
            if (depositAmount < 0) {
                throw new IllegalArgumentException("Deposit amount cannot be negative.");
            }

            // Add the deposit amount to the balance
            bankBalance.setBalance(bankBalance.getBalance() + depositAmount);
            System.out.println("Deposit successful");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to check the balance
    public void checkBalance(){
        System.out.println("Your current balance is $" + bankBalance.getBalance());
    }
}

class BankBalance{
    // Initial balance
    private double balance = 0;

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Setter for balance
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
