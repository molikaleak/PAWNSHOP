package Pawn.Class;

import Pawn.Class.Authentication.AccountingProfile;
import Pawn.Class.Authentication.BranchManagerProfile;
import Pawn.Class.Authentication.SalesProfile;
import Pawn.Class.Authentication.UserProfile;
import Pawn.GUI.MenuGUI;

import java.util.ArrayList;

public class Userclass extends UserProfile {
    public static ArrayList<UserProfile> userArray = new ArrayList<>();

    // Constructor for creating a new user with complete information only Owner can create new user
    public Userclass(String userId, String userName, String password) {
        super(userId, userName, password);
    }

    // Constructor for login I only need user ID and Password that already been set
    public Userclass(String userId, String password) {
        super(userId, password);
    }

    // Method for signing in
    public static UserProfile signIn(String userId, String password) {
        for (UserProfile userProfile : userArray) {
            if (userProfile.getUserId().equals(userId) && userProfile.getPassword().equals(password)) {
                return userProfile; // Return the matched user
            }
        }
        return null; // Authentication failed
    }

    // Method to create initial users with unique IDs
    public static void createUsers() {
        userArray.add(new SalesProfile("001", "SalePerson", "camtech123"));
        userArray.add(new BranchManagerProfile("002", "BranchManager", "camtech1234"));
        userArray.add(new AccountingProfile("003", "Accountant", "camtech12345"));
    }

    @Override
    public void displayMenu() {
        // Implement a default menu or leave it abstract, depending on your design
    }

    public static void loginUser(String ID , String Password) {
        createUsers(); // Create initial users

        String userId; // Initialize userId as String
        while (true) {
            if (!ID.isEmpty()) {
                break; // Exit loop if a valid ID is entered
            }
            System.out.println("User ID cannot be empty. Please enter a valid user ID.");
            break;
        }

        UserProfile user = signIn(ID, Password); // Use string userId for signIn

        if (user != null) {
            System.out.println("Login Successful: " + user.getUserName());
            new MenuGUI(user);
        } else {
            System.out.println("Login Failed");
        }
    }
}