package Pawn.Class.Authentication;

import Pawn.System.Repayment.RepaymentLoan;
import Pawn.System.CustomerManager;

import java.util.Scanner;

public class SalesProfile extends UserProfile {
    private CustomerManager CustomerManager;
    private RepaymentLoan repaymentLoan;

    public SalesProfile(String userId, String userName, String password) {
        super(userId, userName, password);
    }



    @Override
    public void displayMenu() {
        this.CustomerManager = new CustomerManager(); // Initialize the customer manager
        this.repaymentLoan = new RepaymentLoan();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Sales Menu:");
            System.out.println("1. Calculate Payment Schedule");
            System.out.println("2. Create Customer and Contract");
            System.out.println("3. Receive Installment");
            System.out.println("4. Report");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    repaymentLoan.startRepaymentProcess();
                    break;
                case 2:
                    CustomerManager.start();
                    break;
                case 3:
                    receiveInstallment();
                    break;
                case 4:
                    generateReport();
                    break;
                case 0:
                    System.out.println("Exiting Sales Menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }


    private void receiveInstallment() {
        System.out.println("Receiving Installment...");
    }

    private void generateReport() {
        System.out.println("Generating Report...");
    }
}