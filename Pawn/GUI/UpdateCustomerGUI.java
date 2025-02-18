package Pawn.GUI;

import Pawn.Class.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class UpdateCustomerGUI extends JFrame {
    private JTextField customerNameField;
    private JTextField newPhoneNumberField;
    private JTextField newContactMethodField;
    private JTextArea messageArea;
    private static final String CUSTOMER_FILE = "mycustomerdataset.txt";

    public UpdateCustomerGUI() {
        setTitle("Update Customer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Create input fields
        add(new JLabel("Customer Name:"));
        customerNameField = new JTextField();
        add(customerNameField);

        add(new JLabel("New Phone Number:"));
        newPhoneNumberField = new JTextField();
        add(newPhoneNumberField);

        add(new JLabel("New Contact Method:"));
        newContactMethodField = new JTextField();
        add(newContactMethodField);

        JButton updateButton = new JButton("Update Customer");
        updateButton.addActionListener(new UpdateCustomerListener());
        add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(messageArea);

        setVisible(true);
    }

    private class UpdateCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateCustomer();
        }
    }

    private void updateCustomer() {
        String name = customerNameField.getText().trim();
        Customer customer = findCustomerByName(name);

        if (customer != null) {
            String newPhoneNumber = newPhoneNumberField.getText().trim();
            String newContactMethod = newContactMethodField.getText().trim();

            if (!newPhoneNumber.isEmpty()) {
                customer.setPhone_number(newPhoneNumber);
            }
            if (!newContactMethod.isEmpty()) {
                customer.setContact_method(newContactMethod);
            }

            messageArea.setText("Customer updated: " + customer);
            saveCustomersToFile();
        } else {
            messageArea.setText("Customer not found: " + name);
        }
    }

    private Customer findCustomerByName(String name) {
        List<Customer> customers = loadCustomersFromFile();
        return customers.stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    private List<Customer> loadCustomersFromFile() {
        List<Customer> customers = new ArrayList<>();
        Path filePath = Path.of(CUSTOMER_FILE);

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(", ");
                if (parts.length < 3) continue; // Ensure there are enough parts

                String name = parts[0].split(": ")[1].trim();
                String phoneNumber = parts[1].split(": ")[1].trim();
                String contactMethod = parts[2].split(": ")[1].trim();
                customers.add(new Customer(name, phoneNumber, contactMethod));
            }
        } catch (IOException e) {
            messageArea.setText("Error reading customers file: " + e.getMessage());
        }
        return customers;
    }

    private void saveCustomersToFile() {
        Path filePath = Path.of(CUSTOMER_FILE);
        StringBuilder customerData = new StringBuilder();

        List<Customer> customers = loadCustomersFromFile(); // Load existing customers
        for (Customer customer : customers) {
            customerData.append(String.format("Name: %s, Phone: %s, Contact Method: %s%n",
                    customer.getName(), customer.getPhoneNumber(), customer.getContact_method()));
        }

        try {
            Files.writeString(filePath, customerData.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            messageArea.setText("Error saving customers to file: " + e.getMessage());
        }
    }

}