//package Pawn;
//
//import Pawn.Class.Employee;
//import Pawn.System.Calculator.LoanManagementSystem;
//import Pawn.System.LoginManager;
//import Pawn.System.MenuPO;
//
//import java.util.Scanner;
//
//public class Main {
//
//    private static LoanManagementSystem loanManagementSystem;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        LoginManager loginManager = new LoginManager();
//        LoanManagementSystem loanManagementSystem = new LoanManagementSystem();
//        System.out.println("Welcome to the Loan Management System!");
//        loginManager.populateInitialEmployees();
//
//        System.out.println("Welcome to the Loan Management System!");
//
//        while (true) {
//            System.out.println("\n1. Sign Up");
//            System.out.println("2. Login");
//            System.out.println("3. Quit");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume the newline character
//
//            switch (choice) {
//                case 1:
//                    loginManager.signUp(scanner);
//                    break;
//                case 2:
//                    if (login(scanner, loginManager)) {
//                        MenuPO menuPO = new MenuPO(); // Create a new MenuPO instance
//                        menuPO.displayMenu(scanner); // Start the menu after successful login
//                    }
//                    break;
//                case 3:
//                    System.exit(0);
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
//
//    private static boolean login(Scanner scanner, LoginManager loginManager) {
//        System.out.print("Enter your name: ");
//        String name = scanner.nextLine();
//
//        if (!loginManager.getUserDatabase().containsKey(name)) {
//            System.out.println("Username does not exist. Please sign up.");
//            return false;
//        }
//
//        System.out.print("Enter your password: ");
//        String password = scanner.nextLine();
//
//        Employee employee = loginManager.getUserDatabase().get(name);
//        if (!employee.getPassword().equals(password)) {
//            System.out.println("Invalid username or password. Please try again.");
//            return false;
//        } else {
//            System.out.println("Welcome, " + name + "!");
//            return true; // Successful login
//        }
//    }
//}