package Pawn.Class.Authentication;

import java.util.Scanner;

public class BranchManagerProfile extends UserProfile {
    public BranchManagerProfile(String userId, String userName, String password) {
        super(userId, userName, password); // Call to the superclass constructor
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Branch Manager Menu:");
            System.out.println("1. Approve Applicant");
            System.out.println("2. View Contract Information");
            System.out.println("3. Installment Report");
            System.out.println("4. General Report");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: approveApplicant(); break;
                case 2: viewContractInformation(); break;
                case 3: installmentReport(); break;
                case 4: generateGeneralReport(); break;
                case 0: System.out.println("Exiting Branch Manager Menu."); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }



    // Example methods for each option
    private void approveApplicant() {
        System.out.println("Approving Applicant...");
    }

    private void viewContractInformation() {
        System.out.println("Viewing Contract Information...");
    }

    private void installmentReport() {
        System.out.println("Generating Installment Report...");
    }

    private void generateGeneralReport() {
        System.out.println("Generating General Report...");
    }


}