package Pawn.System;

import Pawn.Class.Customer;
import Pawn.System.Repayment.RepaymentLoan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static Pawn.System.Repayment.RepaymentLoan.repaymentCalculators;

public class CustomerManager {
    private static List<Customer> customers; // List to store customers
    private static Scanner scanner;
    private static final String CUSTOMER_FILE = "mycustomerdataset.txt";
    private static final String CONTRACT_FILE = "contractdata.txt";
    private static List<Integer> loanTypeIdsUsed = new ArrayList<>(); // List to track loan type IDs

    public CustomerManager() {
        this.customers = loadCustomersFromFile(); // Load customers from file
        this.scanner = new Scanner(System.in); // Initialize the scanner
    }

    // Method to start managing customers
    public static void start() {
        String choice;
        do {
            displayMenu(); // Display the menu
            choice = scanner.nextLine();

            if (!isValidChoice(choice)) {
                System.out.println("Invalid choice, please enter a number between 1 and 8.");
                continue; // Skip to the next iteration if the choice is invalid
            }

            switch (choice) {
                case "1":
                    createContract(); // Create a contract
                    break;
                case "2":
                    removeCustomer();
                    break;
                case "3":
                    listCustomers();
                    break;
                case "4":
                    saveCustomersToFile();
                    break;
                case "5":
                    viewOldContracts(); // View old contracts
                    break;
                case "6":
                    updateCustomer(); // Update customer details
                    break;
                case "7":
                    generateReport(); // Generate a report
                    break;
                case "8":
                    addCustomer();
                case"9":
                    System.out.println("Exiting...");
                    break;
                default:
                    break;
            }
        } while (!choice.equals("9"));
    }

    // Method to display the menu
    private static void displayMenu() {
        System.out.println("\nCustomer Management System");
        System.out.println("1. Create New Contract");
        System.out.println("2. Remove Customer");
        System.out.println("3. List Customers");
        System.out.println("4. Save Customers to File");
        System.out.println("5. View Old Contracts");
        System.out.println("6. Update Customer");
        System.out.println("7. Generate Report");
        System.out.println("8. Add Customer");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    // Method to validate the user's choice
    private static boolean isValidChoice(String choice) {
        return choice.matches("[1-8]"); // Check if the choice is between 1 and 8
    }

    // Method to create a new contract
    private static void createContract() {
        System.out.print("Enter customer name for the contract: ");
        String customerName = scanner.nextLine();

        Customer customer = findCustomerByName(customerName);
        if (customer == null) {
            System.out.println("Customer not found. Would you like to add this customer? (yes/no)");
            String addCustomerResponse = scanner.nextLine();
            if (addCustomerResponse.equalsIgnoreCase("yes")) {
                addCustomer();
                customer = customers.get(customers.size() - 1); // Get the newly added customer
            } else {
                return; // Exit if the customer is not added
            }
        }

        System.out.println("Select loan type:");
        System.out.println("1. Jewelry");
        System.out.println("2. Collectibles");
        System.out.println("3. Electronics");
        System.out.println("4. Luxury Items");
        System.out.println("5. Musical Instruments");
        System.out.println("6. Precious Metals");
        int loanTypeId = Integer.parseInt(scanner.nextLine());

        // Add the loan type ID to the list
        loanTypeIdsUsed.add(loanTypeId);

        System.out.print("Enter loan amount: ");
        double loanAmount = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter loan duration (in months): ");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the start date for repayment (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        // Calculate repayment amount using the loan amount, duration, and loan type
        double repaymentAmount = RepaymentLoan.calculateRepaymentAmount(loanAmount, duration, loanTypeId);
        String nextRepaymentDate = calculateNextRepaymentDate(startDate);

        // Save the contract details to a file, including loanTypeId
        saveContractToFile(customer, loanAmount, duration, repaymentAmount, nextRepaymentDate, startDate, loanTypeId);
    }

    // Method to add a new customer
    private static void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter preferred contact method (e.g., email, phone): ");
        String contactMethod = scanner.nextLine();

        // Create a new Customer object and add it to the list
        Customer newCustomer = new Customer(name, phoneNumber, contactMethod);
        customers.add(newCustomer);
        System.out.println("Customer added: " + newCustomer);
    }

    // Method to find a customer by name
    private static Customer findCustomerByName(String name) {
        return customers.stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // Method to remove a customer by name
    private static void removeCustomer() {
        System.out.print("Enter customer name to remove: ");
        String name = scanner.nextLine();

        boolean removed = customers.removeIf(customer -> customer.getName().equalsIgnoreCase(name));
        if (removed) {
            System.out.println("Customer removed: " + name);
        } else {
            System.out.println("Customer not found: " + name);
        }
    }

    // Method to list all customers
    public static void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    // Load customers from a file
    private static List<Customer> loadCustomersFromFile() {
        List<Customer> customerList = new ArrayList<>();
        Path filePath = Path.of(CUSTOMER_FILE);

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(", ");
                if (parts.length >= 3) {
                    try {
                        String name = parts[0].split(": ")[1].trim();
                        String phoneNumber = parts[1].split(": ")[1].trim();
                        String contactMethod = parts[2].split(": ")[1].trim();
                        customerList.add(new Customer(name, phoneNumber, contactMethod));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Malformed line: " + line);
                    }
                } else {
                    System.err.println("Insufficient data in line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading customers: " + e.getMessage());
        }

        return customerList;
    }

    // Save customers to a file
    private static void saveCustomersToFile() {
        Path filePath = Path.of(CUSTOMER_FILE);
        StringBuilder data = new StringBuilder();

        for (Customer customer : customers) {
            data.append(String.format("Name: %s, Phone: %s, Contact Method: %s%n",
                    customer.getName(), customer.getPhoneNumber(), customer.getContact_method()));
        }

        try {
            Files.writeString(filePath, data.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Customers saved to file: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    // Save contract details to a file
    private static void saveContractToFile(Customer customer, double loanAmount, int duration, double repaymentAmount, String nextRepaymentDate, String startDate, int loanTypeId) {
        Path filePath = Path.of(CONTRACT_FILE);

        String contractEntry = String.format("Customer: %s, Phone: %s, Loan Amount: %.2f, Duration: %d months, Repayment Amount: %.2f, Start Date: %s, Next Repayment Due: %s, Loan Type ID: %d%n",
                customer.getName(), customer.getPhoneNumber(), loanAmount, duration, repaymentAmount, startDate, nextRepaymentDate, loanTypeId);

        try {
            Files.writeString(filePath, contractEntry, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Contract saved to file: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving contract to file: " + e.getMessage());
        }
    }

    // Calculate the next repayment date after one month
    private static String calculateNextRepaymentDate(String startDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate nextRepaymentDate = start.plusMonths(1); // Add one month
        return nextRepaymentDate.toString(); // Return in "YYYY-MM-DD" format
    }

    // View old contracts
    private static void viewOldContracts() {
        Path filePath = Path.of(CONTRACT_FILE);

        try {
            List<String> contracts = Files.readAllLines(filePath);
            if (contracts.isEmpty()) {
                System.out.println("No old contracts found.");
            } else {
                System.out.println("Old Contracts:");
                for (String contract : contracts) {
                    System.out.println(contract);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading contracts file: " + e.getMessage());
        }
    }

    // Update customer information
    private static void updateCustomer() {
        System.out.print("Enter customer name to update: ");
        String name = scanner.nextLine();
        Customer customer = findCustomerByName(name);

        if (customer != null) {
            System.out.print("Enter new phone number (leave blank to keep current): ");
            String newPhoneNumber = scanner.nextLine();
            if (!newPhoneNumber.isEmpty()) {
                customer.setPhone_number(newPhoneNumber);
            }

            System.out.print("Enter new preferred contact method (leave blank to keep current): ");
            String newContactMethod = scanner.nextLine();
            if (!newContactMethod.isEmpty()) {
                customer.setContact_method(newContactMethod);
            }

            System.out.println("Customer updated: " + customer);

            // Save updated customer data to file
            saveCustomersToFile();
        } else {
            System.out.println("Customer not found: " + name);
        }
    }

    // Generate report
    private static void generateReport() {
        System.out.println("Generating Report...");

        int totalContracts = 0;
        double totalAmountPawned = 0;
        List<Integer> loanTypeIds = new ArrayList<>();

        Path contractFilePath = Path.of(CONTRACT_FILE);
        try {
            List<String> contracts = Files.readAllLines(contractFilePath);
            totalContracts = contracts.size();

            for (String contract : contracts) {
                // Parse the contract string to extract data for calculations
                String[] parts = contract.split(", ");
                if (parts.length < 7) continue; // Ensure there are enough parts

                try {
                    // Extract loan amount
                    double loanAmount = Double.parseDouble(parts[2].split(": ")[1]);
                    totalAmountPawned += loanAmount;

                    // Extract loan type ID
                    int loanTypeId = Integer.parseInt(parts[7].split(": ")[1]); // Adjust index for loan type ID
                    loanTypeIds.add(loanTypeId);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing contract data: " + e.getMessage());
                }
            }

            double averagePawnAmount = totalContracts == 0 ? 0 : totalAmountPawned / totalContracts;

            System.out.printf("Total Contracts: %d%n", totalContracts);
            System.out.printf("Total Amount Pawned: %.2f%n", totalAmountPawned);
            System.out.printf("Average Pawn Amount: %.2f%n", averagePawnAmount);

            // Calculate the most popular loan type ID
            if (!loanTypeIds.isEmpty()) {
                Map<Integer, Long> loanTypeCount = loanTypeIds.stream()
                        .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

                Optional<Map.Entry<Integer, Long>> mostPopular = loanTypeCount.entrySet().stream()
                        .max(Map.Entry.comparingByValue());

                if (mostPopular.isPresent()) {
                    int loanTypeId = mostPopular.get().getKey();
                    long count = mostPopular.get().getValue();
                    String loanTypeName = repaymentCalculators.get(loanTypeId).toString(); // Use the map from LoanManagement

                    System.out.printf("Most Popular Loan Type: %s (ID: %d), Count: %d%n",
                            loanTypeName != null ? loanTypeName : "Unknown", loanTypeId, count);
                }
            } else {
                System.out.println("No loan types were recorded.");
            }
        } catch (IOException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }
}

//package Pawn.System;
//
//import Pawn.Class.Customer;
//import Pawn.System.Repayment.RepaymentLoan;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardOpenOption;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class CustomerManager {
//    private static List<Customer> customers; // List to store customers
//    private static Scanner scanner;
//    private static RepaymentLoan repaymentLoan; // Instance of RepaymentLoan
//
//    public CustomerManager() {
//        this.customers = new ArrayList<>();
//        this.scanner = new Scanner(System.in); // Initialize the scanner
//        this.repaymentLoan = new RepaymentLoan(); // Initialize the repayment loan
//    }
//
//    // Method to start managing customers
//    public static void start() {
//        String choice;
//        do {
//            displayMenu(); // Display the menu
//            choice = scanner.nextLine();
//
//            if (!isValidChoice(choice)) {
//                System.out.println("Invalid choice, please enter a number between 1 and 6.");
//                continue; // Skip to the next iteration if the choice is invalid
//            }
//
//            switch (choice) {
//                case "1":
//                    createContract(); // Create a contract
//                    break;
//                case "2":
//                    removeCustomer();
//                    break;
//                case "3":
//                    listCustomers();
//                    break;
//                case "4":
//                    saveCustomersToFile();
//                    break;
//                case "5":
//                    viewOldContracts(); // View old contracts
//                    break;
//                case "6":
//                    System.out.println("Exiting...");
//                    break;
//                default:
//                    // This case will never be reached due to the isValidChoice check
//                    break;
//            }
//        } while (!choice.equals("6"));
//    }
//
//    // Method to display the menu
//    private static void displayMenu() {
//        System.out.println("\nCustomer Management System");
//        System.out.println("1. Create New Contract");
//        System.out.println("2. Remove Customer");
//        System.out.println("3. List Customers");
//        System.out.println("4. Save Customers to File");
//        System.out.println("5. View Old Contracts");
//        System.out.println("6. Exit");
//        System.out.print("Enter your choice: ");
//    }
//
//    // Method to validate the user's choice
//    private static boolean isValidChoice(String choice) {
//        return choice.matches("[1-6]"); // Check if the choice is between 1 and 6
//    }
//
//    // Method to create a new contract
//    private static void createContract() {
//        System.out.print("Enter customer name for the contract: ");
//        String customerName = scanner.nextLine();
//
//        Customer customer = findCustomerByName(customerName);
//        if (customer == null) {
//            System.out.println("Customer not found. Would you like to add this customer? (yes/no)");
//            String addCustomerResponse = scanner.nextLine();
//            if (addCustomerResponse.equalsIgnoreCase("yes")) {
//                addCustomer();
//                customer = customers.get(customers.size() - 1); // Get the newly added customer
//            } else {
//                return; // Exit if the customer is not added
//            }
//        }
//
//        System.out.print("Enter loan amount: ");
//        double loanAmount = Double.parseDouble(scanner.nextLine());
//
//        System.out.print("Enter loan duration (in months): ");
//        int duration = Integer.parseInt(scanner.nextLine());
//
//        System.out.println("Select loan type:");
//        System.out.println("1. Jewelry");
//        System.out.println("2. Collectibles");
//        System.out.println("3. Electronics");
//        System.out.println("4. Luxury Items");
//        System.out.println("5. Musical Instruments");
//        System.out.println("6. Precious Metals");
//        int loanTypeId = Integer.parseInt(scanner.nextLine());
//
//        System.out.print("Enter the start date for repayment (YYYY-MM-DD): ");
//        String startDate = scanner.nextLine();
//
//        // Calculate repayment amount using the loan amount, duration, and loan type
//        double repaymentAmount = repaymentLoan.calculateRepaymentAmount(loanAmount, duration, loanTypeId);
//        String nextRepaymentDate = calculateNextRepaymentDate(startDate);
//
//        // Save the contract details to a file
//        saveContractToFile(customer, loanAmount, duration, repaymentAmount, nextRepaymentDate, startDate);
//    }
//
//    // Method to add a new customer
//    private static void addCustomer() {
//        System.out.print("Enter customer name: ");
//        String name = scanner.nextLine();
//
//        System.out.print("Enter customer phone number: ");
//        String phoneNumber = scanner.nextLine();
//
//        System.out.print("Enter preferred contact method (e.g., email, phone): ");
//        String contactMethod = scanner.nextLine();
//
//        // Create a new Customer object and add it to the list
//        Customer newCustomer = new Customer(name, phoneNumber, contactMethod);
//        customers.add(newCustomer);
//        System.out.println("Customer added: " + newCustomer);
//    }
//
//    // Method to find a customer by name
//    private static Customer findCustomerByName(String name) {
//        return customers.stream()
//                .filter(customer -> customer.getName().equalsIgnoreCase(name))
//                .findFirst()
//                .orElse(null);
//    }
//
//    // Method to remove a customer by name
//    private static void removeCustomer() {
//        System.out.print("Enter customer name to remove: ");
//        String name = scanner.nextLine();
//
//        boolean removed = customers.removeIf(customer -> customer.getName().equalsIgnoreCase(name));
//        if (removed) {
//            System.out.println("Customer removed: " + name);
//        } else {
//            System.out.println("Customer not found: " + name);
//        }
//    }
//
//    // Method to list all customers
//    public static void listCustomers() {
//        if (customers.isEmpty()) {
//            System.out.println("No customers found.");
//        } else {
//            for (Customer customer : customers) {
//                System.out.println(customer);
//            }
//        }
//    }
//
//    // Method to save customers to a file
//    private static void saveCustomersToFile() {
//        Path filePath = Path.of("mycustomerdataset.txt");
//        StringBuilder data = new StringBuilder();
//
//        for (Customer customer : customers) {
//            data.append(customer.toString()).append(System.lineSeparator());
//        }
//
//        try {
//            Files.writeString(filePath, data.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//            System.out.println("Customers saved to file: " + filePath.toAbsolutePath());
//        } catch (IOException e) {
//            System.err.println("Error saving to file: " + e.getMessage());
//        }
//    }
//
//    // Method to save contract details to a file
//    private static void saveContractToFile(Customer customer, double loanAmount, int duration, double repaymentAmount, String nextRepaymentDate, String startDate) {
//        Path filePath = Path.of("contractdata.txt");
//
//        String contractEntry = String.format("Customer: %s, Phone: %s, Loan Amount: %.2f, Duration: %d months, Repayment Amount: %.2f, Start Date: %s, Next Repayment Due: %s%n",
//                customer.getName(), customer.getPhoneNumber(), loanAmount, duration, repaymentAmount, startDate, nextRepaymentDate);
//
//        try {
//            Files.writeString(filePath, contractEntry, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//            System.out.println("Contract saved to file: " + filePath.toAbsolutePath());
//        } catch (IOException e) {
//            System.err.println("Error saving contract to file: " + e.getMessage());
//        }
//    }
//
//    // Method to calculate the next repayment date after one month
//    private static String calculateNextRepaymentDate(String startDate) {
//        // Assuming startDate is in the format "YYYY-MM-DD"
//        LocalDate start = LocalDate.parse(startDate);
//        LocalDate nextRepaymentDate = start.plusMonths(1); // Add one month
//        return nextRepaymentDate.toString(); // Return in "YYYY-MM-DD" format
//    }
//
//    // Method to view old contracts
//    private static void viewOldContracts() {
//        Path filePath = Path.of("contractdata.txt");
//
//        try {
//            List<String> contracts = Files.readAllLines(filePath);
//            if (contracts.isEmpty()) {
//                System.out.println("No old contracts found.");
//            } else {
//                System.out.println("Old Contracts:");
//                for (String contract : contracts) {
//                    System.out.println(contract);
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading contracts file: " + e.getMessage());
//        }
//    }
//}
////package Pawn.System;
////
////import Pawn.Class.Customer;
////
////import java.io.IOException;
////import java.nio.file.Files;
////import java.nio.file.Path;
////import java.nio.file.StandardOpenOption;
////import java.util.ArrayList;
////import java.util.List;
////import java.util.Scanner;
////
////public class CustomerManager {
////    private static List<Customer> customers; // List to store customers
////    private static Scanner scanner;
////
////    public CustomerManager() {
////        this.customers = new ArrayList<>();
////        this.scanner = new Scanner(System.in); // Initialize the scanner
////    }
////
////    // Method to start managing customers
////    public static void start() {
////        String choice;
////        do {
////            System.out.println("\nCustomer Management System");
////            System.out.println("1. Add Customer");
////            System.out.println("2. Remove Customer");
////            System.out.println("3. List Customers");
////            System.out.println("4. Save Customers to File");
////            System.out.println("5. Exit");
////            System.out.print("Enter your choice: ");
////            choice = scanner.nextLine();
////
////            switch (choice) {
////                case "1":
////                    addCustomer();
////                    break;
////                case "2":
////                    removeCustomer();
////                    break;
////                case "3":
////                    listCustomers();
////                    break;
////                case "4":
////                    saveCustomersToFile();
////                    break;
////                case "5":
////                    System.out.println("Exiting...");
////                    break;
////                default:
////                    System.out.println("Invalid choice, please try again.");
////            }
////        } while (!choice.equals("5"));
////    }
////
////    // Method to add a new customer
////    private static void addCustomer() {
////        System.out.print("Enter customer name: ");
////        String name = scanner.nextLine();
////
////        System.out.print("Enter customer phone number: ");
////        String phoneNumber = scanner.nextLine();
////
////        System.out.print("Enter preferred contact method (e.g., email, phone): ");
////        String contactMethod = scanner.nextLine();
////
////        // Create a new Customer object and add it to the list
////        Customer newCustomer = new Customer(name, phoneNumber, contactMethod);
////        customers.add(newCustomer);
////        System.out.println("Customer added: " + newCustomer);
////    }
////
////    // Method to remove a customer by name
////    private static void removeCustomer() {
////        System.out.print("Enter customer name to remove: ");
////        String name = scanner.nextLine();
////
////        boolean removed = customers.removeIf(customer -> customer.getName().equalsIgnoreCase(name));
////        if (removed) {
////            System.out.println("Customer removed: " + name);
////        } else {
////            System.out.println("Customer not found: " + name);
////        }
////    }
////
////    // Method to list all customers
////    public static void listCustomers() {
////        if (customers.isEmpty()) {
////            System.out.println("No customers found.");
////        } else {
////            for (Customer customer : customers) {
////                System.out.println(customer);
////            }
////        }
////    }
////
////    // Method to save customers to a file
////    private static void saveCustomersToFile() {
////        Path filePath = Path.of("mycustomerdataset.txt");
////        StringBuilder data = new StringBuilder();
////
////        for (Customer customer : customers) {
////            data.append(customer.toString()).append(System.lineSeparator());
////        }
////
////        try {
////            Files.writeString(filePath, data.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
////            System.out.println("Customers saved to file: " + filePath.toAbsolutePath());
////        } catch (IOException e) {
////            System.err.println("Error saving to file: " + e.getMessage());
////        }
////    }
////}