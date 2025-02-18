package Pawn.System;

import Pawn.Class.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginManager {

    public static Map<String, Employee> userDatabase = new HashMap<>(); // Store username and Employee object

    public LoginManager() {
        // Optional: Load initial employees or dataset here
        populateInitialEmployees(); // Load hardcoded employees
        // Optionally load from file
        // loadDataFromFile("path/to/dataset.csv");
    }

    public static void LoginTest(String a, String b) {
        System.out.println("Attempting to log in with username: " + a);
        System.out.println("Current users in database: " + userDatabase.keySet());

        if (!userDatabase.containsKey(a)) {
            System.out.println("Username does not exist. Please sign up.");
            return;
        }

        Employee employee = userDatabase.get(a);
        if (!employee.getPassword().equals(b)) {
            System.out.println("Invalid username or password. Please try again.");
        } else {
            System.out.println("Welcome, " + a + "!");
        }
    }

    public void signUp(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        if (userDatabase.containsKey(name)) {
            System.out.println("Username already exists. Please choose a different one.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Employee newEmployee = new Employee("employee" + (userDatabase.size() + 1), "branch001", name, "0000000000", "address", password);
        userDatabase.put(name, newEmployee);
        System.out.println("Sign up successful!");
    }

    public void login(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Employee employee = userDatabase.get(name);
        if (!userDatabase.containsKey(name)) {
            System.out.println("Username does not exist. Please sign up.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (!employee.getPassword().equals(password)) {
            System.out.println("Invalid username or password. Please try again.");
        } else {
            System.out.println("Welcome, " + name + "!");
        }
    }

    public void populateInitialEmployees() {
        // Adding initial employees
        Employee emp1 = new Employee("employ001", "branch001", "cutie", "02342309042", "pp", "pp-1111");
        Employee emp2 = new Employee("employ002", "branch001", "meow", "02342309043", "pp", "pp-2222");
        Employee emp3 = new Employee("employ003", "branch002", "malen", "02342309044", "kpc", "kpc-2222");
        Employee emp4 = new Employee("employ004", "branch002", "lolita", "02342309045", "kpc", "kpc-2222");

        // Register employees in the user database
        userDatabase.put(emp1.getName(), emp1);
        userDatabase.put(emp2.getName(), emp2);
        userDatabase.put(emp3.getName(), emp3);
        userDatabase.put(emp4.getName(), emp4);
    }

    public void loadDataFromFile(String filePath) {
        // Load data from a CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Assuming CSV format
                if (data.length >= 5) {
                    String name = data[0];
                    String branch = data[1];
                    String username = data[2];
                    String phone = data[3];
                    String address = data[4];
                    String password = data[5];

                    Employee newEmployee = new Employee(name, branch, username, phone, address, password);
                    userDatabase.put(username, newEmployee);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public Map<String, Employee> getUserDatabase() {
        return userDatabase;
    }
}