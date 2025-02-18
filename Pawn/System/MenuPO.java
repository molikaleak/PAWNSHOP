package Pawn.System;

import Pawn.System.Repayment.RepaymentLoan;

import java.util.Scanner;

public class MenuPO {
    // You can declare instances for managers or services here if needed
    private CustomerManager CustomerManager; // Instance of AddCustomerManager
    private RepaymentLoan repaymentLoan;

    public MenuPO() {
        this.CustomerManager = new CustomerManager(); // Initialize the customer manager
        this.repaymentLoan = new RepaymentLoan();
    }

    public void displayMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Calculate RePayment Schedule");
            System.out.println("2. Check Rule of Pawn Shop");
            System.out.println("3. Create Customer");
            System.out.println("4. Create Contract");
            System.out.println("   4.1. Create Applicant");
            System.out.println("   4.2. Activate Contract");
            System.out.println("   4.3. Print Contract For Customer");
            System.out.println("   4.4. View Payment Schedule");
            System.out.println("5. Receive Installment");
            System.out.println("   5.1. Installment On Schedule");
            System.out.println("   5.2. Wave Penalty");
            System.out.println("   5.3. Receive Installment (renew, redeem, prepaid, installment)");
            System.out.println("6. Report");
            System.out.println("   6.1. Close/Redeem");
            System.out.println("   6.2. Reject/Cancel");
            System.out.println("   6.3. Report Installment Paid");
            System.out.println("   6.4. Report Payment History");
            System.out.println("   6.5. Wave Penalty History");
            System.out.println("   6.6. Stock Report");
            System.out.println("   6.7. Payment Detail");
            System.out.println("   6.8. All Contracts");
            System.out.println("   6.9. All Customers");
            System.out.println("7. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    RepaymentLoan.startRepaymentProcess();
                    break;
                case 2:
                    checkRuleOfPawnShop();
                    break;
                case 3:
                    Pawn.System.CustomerManager.start();
                    break;
                case 4:
                    createContractMenu(scanner);
                    break;
                case 5:
                    receiveInstallmentMenu(scanner);
                    break;
                case 6:
                    reportMenu(scanner);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return; // Exit the menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void calculatePaymentSchedule() {
        // Implement logic to calculate payment schedule
        System.out.println("Calculating payment schedule...");
    }

    private void checkRuleOfPawnShop() {
        // Implement logic to check rules of the pawn shop
        System.out.println("Checking rules of the pawn shop...");
    }

    private void createCustomer(Scanner scanner) {
        // Implement logic to create a customer
        System.out.println("Creating customer...");
    }

    private void createContractMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nCreate Contract Menu:");
            System.out.println("1. Create Applicant");
            System.out.println("2. Activate Contract");
            System.out.println("3. Print Contract For Customer");
            System.out.println("4. View Payment Schedule");
            System.out.println("5. Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createApplicant();
                    break;
                case 2:
                    activateContract();
                    break;
                case 3:
                    printContractForCustomer();
                    break;
                case 4:
                    viewPaymentSchedule();
                    break;
                case 5:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createApplicant() {
        // Implement logic to create an applicant
        System.out.println("Creating applicant...");
    }

    private void activateContract() {
        // Implement logic to activate a contract
        System.out.println("Activating contract...");
    }

    private void printContractForCustomer() {
        // Implement logic to print contract for customer
        System.out.println("Printing contract for customer...");
    }

    private void viewPaymentSchedule() {
        // Implement logic to view payment schedule
        System.out.println("Viewing payment schedule...");
    }

    private void receiveInstallmentMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nReceive Installment Menu:");
            System.out.println("1. Installment On Schedule");
            System.out.println("2. Wave Penalty");
            System.out.println("3. Receive Installment (renew, redeem, prepaid, installment)");
            System.out.println("4. Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    receiveInstallmentOnSchedule();
                    break;
                case 2:
                    wavePenalty();
                    break;
                case 3:
                    receiveInstallmentOptions();
                    break;
                case 4:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void receiveInstallmentOnSchedule() {
        // Implement logic to receive installment on schedule
        System.out.println("Receiving installment on schedule...");
    }

    private void wavePenalty() {
        // Implement logic to wave penalty
        System.out.println("Waving penalty...");
    }

    private void receiveInstallmentOptions() {
        // Implement logic for receiving installments
        System.out.println("Receiving installment options...");
    }

    private void reportMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nReport Menu:");
            System.out.println("1. Close/Redeem");
            System.out.println("2. Reject/Cancel");
            System.out.println("3. Report Installment Paid");
            System.out.println("4. Report Payment History");
            System.out.println("5. Wave Penalty History");
            System.out.println("6. Stock Report");
            System.out.println("7. Payment Detail");
            System.out.println("8. All Contracts");
            System.out.println("9. All Customers");
            System.out.println("10. Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    closeRedeem();
                    break;
                case 2:
                    rejectCancel();
                    break;
                case 3:
                    reportInstallmentPaid();
                    break;
                case 4:
                    reportPaymentHistory();
                    break;
                case 5:
                    wavePenaltyHistory();
                    break;
                case 6:
                    stockReport();
                    break;
                case 7:
                    paymentDetail();
                    break;
                case 8:
                    allContracts();
                    break;
                case 9:
                    allCustomers();
                    break;
                case 10:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void closeRedeem() {
        // Implement logic for closing or redeeming
        System.out.println("Closing or redeeming...");
    }

    private void rejectCancel() {
        // Implement logic for rejecting or canceling
        System.out.println("Rejecting or canceling...");
    }

    private void reportInstallmentPaid() {
        // Implement logic to report installment paid
        System.out.println("Reporting installment paid...");
    }

    private void reportPaymentHistory() {
        // Implement logic to report payment history
        System.out.println("Reporting payment history...");
    }

    private void wavePenaltyHistory() {
        // Implement logic for waving penalty history
        System.out.println("Waving penalty history...");
    }

    private void stockReport() {
        // Implement logic for stock report
        System.out.println("Generating stock report...");
    }

    private void paymentDetail() {
        // Implement logic for payment detail report
        System.out.println("Generating payment detail...");
    }

    private void allContracts() {
        // Implement logic to display all contracts
        System.out.println("Displaying all contracts...");
    }

    private void allCustomers() {
        // Implement logic to display all customers
        System.out.println("Displaying all customers...");
    }
}