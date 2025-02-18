package Pawn.Class;

import Pawn.Class.Authentication.UserProfile;

import java.util.Scanner;

import static Pawn.Class.Userclass.createUsers;

public class Main {
    public static void main(String[] args) {
        createUsers(); // Create initial users

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        UserProfile user = Userclass.signIn(userId, password);

        if (user != null) {
            System.out.println("Login Successful: " + user.getUserName());
            user.displayMenu(); // Call the specific menu for the user type
        } else {
            System.out.println("Login Failed");
        }

        scanner.close();
    }
}
