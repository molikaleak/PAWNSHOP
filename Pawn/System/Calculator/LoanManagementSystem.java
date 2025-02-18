package Pawn.System.Calculator;

import Pawn.Class.Loan;

import java.util.*;

public class LoanManagementSystem {
    private List<Loan> loans = new ArrayList<>();
    private Map<Integer, Class<? extends Loan>> loanTypeMap = new HashMap<>(); // Map for loan types

    public LoanManagementSystem() {
        // Initialize the loan type map with IDs and corresponding loan classes
        loanTypeMap.put(1, Loan.Jewelry.class);
        loanTypeMap.put(2, Loan.Collectibles.class);
        loanTypeMap.put(3, Loan.Electronic.class);
        loanTypeMap.put(4, Loan.LuxuryItems.class);
        loanTypeMap.put(5, Loan.MusicalInstruments.class);
        loanTypeMap.put(6, Loan.PreciousMetals.class);
    }

    public void applyForLoan(Loan loan, LoanCalculator calculator) {
        loan.setCalculator(calculator); // Set the appropriate calculator
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter loan amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter term in years: ");
        int term = scanner.nextInt();
        loan.applyForLoan(amount, term);
        loans.add(loan);
    }

    public void displayAllLoans() {
        for (Loan loan : loans) {
            loan.displayLoanDetails();
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAvailable Loan Types:");
            for (Map.Entry<Integer, Class<? extends Loan>> entry : loanTypeMap.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getSimpleName());
            }
            System.out.println("Enter loan type ID to apply (or 0 to exit):");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }

            Class<? extends Loan> loanClass = loanTypeMap.get(choice);
            if (loanClass != null) {
                try {
                    Loan loan = loanClass.getDeclaredConstructor().newInstance(); // Create an instance of the loan
                    LoanCalculator calculator = null;

                    if (loan instanceof Loan.Jewelry) {
                        calculator = new Jewelry();
                    } else if (loan instanceof Loan.Collectibles) {
                        calculator = new Collectibles();
                    } else if (loan instanceof Loan.Electronic) {
                        calculator = new Electronic();
                    } else if (loan instanceof Loan.LuxuryItems) {
                        calculator = new LuxuryItems();
                    } else if (loan instanceof Loan.MusicalInstruments) {
                        calculator = new MusicalInstruments();
                    } else if (loan instanceof Loan.PreciousMetals) {
                        calculator = new PreciousMetals();
                    }

                    applyForLoan(loan, calculator);
                } catch (Exception e) {
                    System.out.println("Error creating loan instance: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid loan type ID. Please try again.");
            }
        }
        displayAllLoans();
    }
}