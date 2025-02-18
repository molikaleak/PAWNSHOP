package Pawn.Class.Authentication;
import Pawn.System.Repayment.RepaymentLoan;
import Pawn.System.CustomerManager;

import java.util.Scanner;

public abstract class UserProfile {
    protected String userId;
    protected String userName;
    protected String password; // Include password for authentication

    public UserProfile(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public UserProfile(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }


    public abstract void displayMenu(); // Each profile will implement its menu

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}