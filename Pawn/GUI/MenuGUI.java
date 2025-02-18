package Pawn.GUI;

import Pawn.Class.Authentication.AccountingProfile;
import Pawn.Class.Authentication.BranchManagerProfile;
import Pawn.Class.Authentication.SalesProfile;
import Pawn.Class.Authentication.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {
    private UserProfile user;

    public MenuGUI(UserProfile user) {
        this.user = user;
        createMenu();
    }

    private void createMenu() {
        // Create the main menu frame
        JFrame menuFrame = new JFrame("User Menu");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(700, 700);
        menuFrame.setLayout(new GridLayout(0, 1)); // Vertical layout for menu items

        // Display different options based on user type
        if (user instanceof SalesProfile) {
            menuFrame.add(new JLabel("Welcome Sales Person: " + user.getUserName()));

            JButton RepaymentBTN = new JButton("Repayment Calculation");
            RepaymentBTN.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Instantiate the LoanRepaymentGUI when the button is clicked
                    SwingUtilities.invokeLater(() -> new LoanRepaymentGUI());
                }
            });

            JButton General = new JButton("General Report");
            General.addActionListener(e -> {
                // Implement sales options here
                SwingUtilities.invokeLater(() -> new ReportGeneratorGUI());
            });

            JButton UpdateCustomer = new JButton(" Update Customer");
            UpdateCustomer.addActionListener(e -> {
                // Implement sales options here
                SwingUtilities.invokeLater(() -> new UpdateCustomerGUI());
            });

            JButton ViewListCustomer = new JButton("View Old Contract");
            ViewListCustomer.addActionListener( e -> {
                SwingUtilities.invokeLater(() -> new ViewOldContractsGUI());
            });


            // Add buttons to the menu frame
            menuFrame.add(RepaymentBTN);
            menuFrame.add(General);
            menuFrame.add(UpdateCustomer);
            menuFrame.add(ViewListCustomer);
        } else if (user instanceof BranchManagerProfile) {
            menuFrame.add(new JLabel("Welcome Branch Manager: " + user.getUserName()));
            JButton managerButton = new JButton("Manager Options");
            managerButton.addActionListener(e -> {
                // Implement manager options here
                JOptionPane.showMessageDialog(menuFrame, "Manager options clicked!");
            });
            menuFrame.add(managerButton);
        } else if (user instanceof AccountingProfile) {
            menuFrame.add(new JLabel("Welcome Accountant: " + user.getUserName()));
            JButton accountingButton = new JButton("Accounting Options");
            accountingButton.addActionListener(e -> {
                // Implement accounting options here
                JOptionPane.showMessageDialog(menuFrame, "Accounting options clicked!");
            });
            menuFrame.add(accountingButton);
        }

// Add exit button to return to login page
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            menuFrame.dispose(); // Close the menu frame
            MainPage.createLoginFrame(); // Show the login frame again
        });
        menuFrame.add(exitButton);

        // Make the menu frame visible
        menuFrame.setVisible(true);
    }
}