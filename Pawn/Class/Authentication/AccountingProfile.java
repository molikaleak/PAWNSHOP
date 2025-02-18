package Pawn.Class.Authentication;


import java.util.Scanner;

public class AccountingProfile extends UserProfile {
    public AccountingProfile(String userId, String userName , String password) {
        super(userId, userName , password);
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Accounting Menu:");
            System.out.println("1. Dashboard");
            System.out.println("2. Calculate Schedule");
            System.out.println("3. Book Journal");
            System.out.println("4. Reports");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: viewDashboard(); break;
                case 2: calculateSchedule(); break;
                case 3: bookJournal(); break;
                case 4: generateReports(); break;
                case 0: System.out.println("Exiting Accounting Menu."); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // Example methods for each option
    private void viewDashboard() {
        System.out.println("Viewing Dashboard...");
    }

    private void calculateSchedule() {
        System.out.println("Calculating Schedule...");
    }

    private void bookJournal() {
        System.out.println("Booking Journal...");
    }

    private void generateReports() {
        System.out.println("Generating Reports...");
    }
}
