import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ATM {
    private float balance;
    private int PIN = 1234;
    private final List<String> transactions;
    private final Scanner sc;

    public ATM(Scanner sc) {
        this.sc = sc;
        this.transactions = new ArrayList<>();
    }

    public void start() {
        if (checkPin()) { // if pin is correct..go to menu
            menu();
        } else {
            System.out.println("Enter a valid PIN");
        }
    }

    private boolean checkPin() {
        System.out.println("Enter PIN: ");
        int enteredPin = sc.nextInt();
        return enteredPin == PIN;
    }

    private void checkBal() {
        System.out.println("Account Balance is " + balance + " Rs.");
    }

    private void withdrawAmt() {
        System.out.println("Enter Amount to withdraw: ");
        float amount = sc.nextFloat();
        if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else {
            balance -= amount;
            transactions.add("Withdrawn: " + amount + " Rs.");
            System.out.println("Money Withdrawal Successful ^_^");
        }
    }

    private void depositAmt() {
        System.out.println("Enter Amount to deposit: ");
        float amount = sc.nextFloat();
        balance += amount;
        transactions.add("Deposited: " + amount + " Rs.");
        System.out.println("Money Deposited Successfully ^_^");
    }

    private void changePin() {
        System.out.println("Enter current PIN: ");
        int enteredPin = sc.nextInt();
        if (enteredPin == PIN) {
            System.out.println("Enter new PIN: ");
            int newPin = sc.nextInt();
            System.out.println("Confirm new PIN: ");
            int confirmPin = sc.nextInt();
            if (newPin == confirmPin) {
                PIN = newPin;
                System.out.println("PIN changed successfully");
            } else {
                System.out.println("PIN mismatch. Try again.");
            }
        } else {
            System.out.println("Incorrect current PIN.");
        }
    }

    private void transferAmt() {
        System.out.println("Enter amount to transfer: ");
        float amount = sc.nextFloat();
        System.out.println("Enter recipient account number: ");
        String recipientAcc = sc.next();
        if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else {
            balance -= amount;
            transactions.add("Transferred: " + amount + " Rs. to account " + recipientAcc);
            System.out.println("Money Transfer Successful ^_^");
        }
    }

    private void viewMiniStatement() {
        System.out.println("Mini Statement: ");
        int count = 0;
        for (int i = transactions.size() - 1; i >= 0 && count < 5; i--, count++) {
            System.out.println(transactions.get(i));
        }
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        }
    }

    private void menu() {
        while (true) {
            System.out.println("Select Option: ");
            System.out.println("1. Check A/C Balance");
            System.out.println("2. Withdraw Amount");
            System.out.println("3. Deposit Amount");
            System.out.println("4. Change PIN");
            System.out.println("5. Transfer Amount");
            System.out.println("6. View Mini Statement");
            System.out.println("7. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    checkBal();
                    break;
                case 2:
                    withdrawAmt();
                    break;
                case 3:
                    depositAmt();
                    break;
                case 4:
                    changePin();
                    break;
                case 5:
                    transferAmt();
                    break;
                case 6:
                    viewMiniStatement();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

public class ATM_Machine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM(sc);
        atm.start();
    }
}
