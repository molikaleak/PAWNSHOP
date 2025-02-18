package Pawn.System.Repayment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class RepaymentLoan {
    public static RepaymentLoan showMostPopularLoanType;
    public static Map<Integer, RepaymentManager> repaymentCalculators = new HashMap<>();
    private static List<Integer> loanTypeIdsUsed = new ArrayList<>(); // List to track loan type IDs

    public RepaymentLoan() {
        // Initialize the repayment calculators for different loan types
        repaymentCalculators.put(1, new Jewelry());
        repaymentCalculators.put(2, new Collectibles());
        repaymentCalculators.put(3, new Electronic());
        repaymentCalculators.put(4, new LuxuryItems());
        repaymentCalculators.put(5, new MusicalInstruments());
        repaymentCalculators.put(6, new PreciousMetals());
    }

    public static void startRepaymentProcess() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose a loan type for repayment:");
            for (Map.Entry<Integer, RepaymentManager> entry : repaymentCalculators.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getClass().getSimpleName());
            }
            System.out.println("Enter loan type ID to calculate repayment (or 0 to exit):");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Exiting repayment process...");
                return;
            }

            RepaymentManager calculator = repaymentCalculators.get(choice);
            if (calculator != null) {
                double loanAmount = 0;
                int duration = 0;

                try {
                    System.out.print("Enter loan amount: ");
                    loanAmount = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter duration in months: ");
                    duration = Integer.parseInt(scanner.nextLine());

                    double monthlyRepayment = calculator.repaymentCalculator(loanAmount, duration);
                    System.out.printf("Monthly repayment for %s: %.2f%n", calculator.getClass().getSimpleName(), monthlyRepayment);

                    // Save the repayment data
                    saveRepaymentData(calculator.getClass().getSimpleName(), loanAmount, duration, monthlyRepayment);

                    // Track the loan type ID used
                    loanTypeIdsUsed.add(choice);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format. Please try again.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Error saving repayment data: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid loan type ID. Please try again.");
            }
        }
    }

    private static void saveRepaymentData(String loanType, double loanAmount, int duration, double monthlyRepayment) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("repayment_data.txt", true))) {
            writer.write(String.format("Loan Type: %s, Loan Amount: %.2f, Duration: %d months, Monthly Repayment: %.2f%n",
                    loanType, loanAmount, duration, monthlyRepayment));
        }
    }


    public static void addLoanTypeId(int loanTypeId) {
        loanTypeIdsUsed.add(loanTypeId);
    }

    // Method to calculate repayment amount based on loan amount, duration, and loan type ID
    public static double calculateRepaymentAmount(double loanAmount, int duration, int loanTypeId) {
        RepaymentManager calculator = repaymentCalculators.get(loanTypeId);
        if (calculator != null) {
            return calculator.repaymentCalculator(loanAmount, duration);
        } else {
            throw new IllegalArgumentException("Invalid loan type ID. Cannot calculate repayment amount.");
        }
    }
}

//    public static void showMostPopularLoanType() {
//        if (loanTypeIdsUsed.isEmpty()) {
//            System.out.println("No loan types have been used yet.");
//            return;
//        }
//
//        Map<Integer, Long> loanTypeCount = loanTypeIdsUsed.stream()
//                .collect(Collectors.groupingBy(id -> id, Collectors.counting()));
//
//        Optional<Map.Entry<Integer, Long>> mostPopular = loanTypeCount.entrySet().stream()
//                .max(Map.Entry.comparingByValue());
//
//        if (mostPopular.isPresent()) {
//            int loanTypeId = mostPopular.get().getKey();
//            long count = mostPopular.get().getValue();
//            System.out.printf("Most Popular Loan Type ID: %d, Count: %d%n", loanTypeId, count);
//        } else {
//            System.out.println("Could not determine the most popular loan type.");
//        }
//    }

//package Pawn.System.Repayment;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class RepaymentLoan {
//    private static Map<Integer, RepaymentManager> repaymentCalculators = new HashMap<>();
//
//    public RepaymentLoan() {
//        // Initialize the repayment calculators for different loan types
//        repaymentCalculators.put(1, new Jewelry());
//        repaymentCalculators.put(2, new Collectibles());
//        repaymentCalculators.put(3, new Electronic());
//        repaymentCalculators.put(4, new LuxuryItems());
//        repaymentCalculators.put(5, new MusicalInstruments());
//        repaymentCalculators.put(6, new PreciousMetals());
//    }
//
//    public static void startRepaymentProcess() {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("\nChoose a loan type for repayment:");
//            for (Map.Entry<Integer, RepaymentManager> entry : repaymentCalculators.entrySet()) {
//                System.out.println(entry.getKey() + ". " + entry.getValue().getClass().getSimpleName());
//            }
//            System.out.println("Enter loan type ID to calculate repayment (or 0 to exit):");
//            int choice = scanner.nextInt();
//
//            if (choice == 0) {
//                System.out.println("Exiting repayment process...");
//                return;
//            }
//
//            RepaymentManager calculator = repaymentCalculators.get(choice);
//            if (calculator != null) {
//                System.out.print("Enter loan amount: ");
//                double loanAmount = scanner.nextDouble();
//                System.out.print("Enter duration in months: ");
//                int duration = scanner.nextInt();
//
//                try {
//                    double monthlyRepayment = calculator.repaymentCalculator(loanAmount, duration);
//                    System.out.printf("Monthly repayment for %s: %.2f%n", calculator.getClass().getSimpleName(), monthlyRepayment);
//                } catch (IllegalArgumentException e) {
//                    System.out.println("Error: " + e.getMessage());
//                }
//            } else {
//                System.out.println("Invalid loan type ID. Please try again.");
//            }
//        }
//    }
//
//}