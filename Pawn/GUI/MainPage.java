package Pawn.GUI;

import Pawn.Class.Authentication.UserProfile;
import Pawn.Class.Userclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainPage {

    public static void main(String[] args) {
        createLoginFrame(); // Initialize the login frame
    }
    public static void createLoginFrame() {
        // Create the main frame
        JFrame firstPage = new JFrame("PawnShopPage");
        firstPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstPage.setSize(1200, 700);
        firstPage.setLayout(new GridLayout(1, 2)); // Split the frame into two columns

        // Load the logo image
        ImageIcon logoIcon = new ImageIcon("/Users/molika/Downloads/Pawn/Pawn/LIB/image-removebg-preview.png");

        // Create a JLabel for the logo image
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER); // Center the image

        // Create a JPanel for the logo
        JPanel logoPanel = new JPanel(new GridLayout(1, 1)); // 1 row, 1 column
        logoPanel.add(logoLabel);
        logoPanel.setBackground(new Color(153, 153, 255)); // Light purple background

        // Login Interface
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS)); // Vertical layout
        panelLogin.setBackground(new Color(255, 255, 255)); // White background
        panelLogin.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50)); // Add padding

//        // Create text fields and button
//        JTextField userID = new JTextField(20);
//        userID.setText("Please Enter Your ID");
//        userID.setMaximumSize(userID.getPreferredSize()); // Set maximum size for layout
//        userID.addFocusListener(new java.awt.event.FocusAdapter() {
//            public void focusGained(java.awt.event.FocusEvent evt) {
//                if (userID.getText().equals("Please Enter Your ID")) {
//                    userID.setText("");
//                }
//            }
//        });

        // Create the user ID text field with placeholder
        JTextField userID = new JTextField(20);
        userID.setText("Please Enter Your ID");
        userID.setForeground(Color.GRAY); // Set placeholder text color
        userID.setMaximumSize(userID.getPreferredSize()); // Set maximum size for layout

        // Focus listener to manage placeholder text
        userID.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (userID.getText().equals("Please Enter Your ID")) {
                    userID.setText("");
                    userID.setForeground(Color.BLACK); // Change text color to black
                }
            }

            public void focusLost(FocusEvent evt) {
                if (userID.getText().isEmpty()) {
                    userID.setText("Please Enter Your ID");
                    userID.setForeground(Color.GRAY); // Reset color for placeholder
                }
            }
        });

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*'); // Mask password input
        passwordField.setMaximumSize(passwordField.getPreferredSize());

        // Placeholder management for the password field
        String placeholder = "Password";
        passwordField.setText(placeholder);
        passwordField.setForeground(Color.GRAY); // Set placeholder text color

        // Focus listener for the password field
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK); // Change text color to black
                    passwordField.setEchoChar('*'); // Ensure it masks input
                }
            }

            public void focusLost(FocusEvent evt) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setForeground(Color.GRAY); // Reset color for placeholder
                    passwordField.setEchoChar((char) 0); // Show placeholder text
                }
            }
        });


        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = userID.getText();
                char[] Password = passwordField.getPassword();

                // Basic validation
                if (ID.isEmpty() || Password.length == 0) {
                    JOptionPane.showMessageDialog(firstPage, "Please enter both ID and Password", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Call loginUser method
                    Userclass.loginUser(ID, new String(Password));
                    UserProfile user = Userclass.signIn(ID, new String(Password));
                    if (user != null) {
                        System.out.println("Login Successful: " + user.getUserName());
                        firstPage.dispose(); // Close the login page
                    } else {
                        JOptionPane.showMessageDialog(firstPage, "Login Failed: Invalid user ID or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        // Add components to the login panel
        panelLogin.add(Box.createVerticalGlue()); // Add vertical glue for centering
        panelLogin.add(userID);
        panelLogin.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between fields
        panelLogin.add(passwordField);
        panelLogin.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between fields
        panelLogin.add(loginBtn);
        panelLogin.add(Box.createVerticalGlue()); // Add vertical glue for centering

        // Add panels to the main frame
        firstPage.add(logoPanel);
        firstPage.add(panelLogin);

        // Make the frame visible
        firstPage.setVisible(true);
    }
}