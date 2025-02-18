package Pawn.GUI;

import Pawn.Class.Customer;
import Pawn.System.Repayment.RepaymentLoan;
import Pawn.System.Repayment.RepaymentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CreateNewContract extends JFrame {
    private List<Customer> customers = new ArrayList<>();
    private static final String CUSTOMER_FILE = "mycustomerdataset.txt";

    public CreateNewContract() {
        // Set up the frame
        setTitle("Create New Contract");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLayout(new GridLayout(8, 1));
        setVisible(true);

        // Create input fields
        JTextField NameJTF = new JTextField();
        JPanel NamePN = createInputPanel("Customer Name", NameJTF);

        JTextField LoanAmountJTF = new JTextField();
        JPanel LoanAmountPN = createInputPanel("Loan Amount", LoanAmountJTF);

        JTextField DurationJTF = new JTextField();
        JPanel DurationPN = createInputPanel("Pawn Duration (months)", DurationJTF);

        JLabel loanTypeLabel = new JLabel("Loan Type:");
        JComboBox<String> loanTypeDropdown = new JComboBox<>(new String[]{
                "Select Loan Type", "Jewelry", "Collectibles", "Electronic", "Luxury Items", "Musical Instruments", "Precious Metals"
        });
        JPanel LoantypePN = new JPanel(new GridLayout(1, 2));
        LoantypePN.add(loanTypeLabel);
        LoantypePN.add(loanTypeDropdown);

        JLabel NextRepayment = new JLabel("Next Repayment Amount: ");
        JLabel NextRepaymentValue = new JLabel();
        JPanel NextRepaymentPN = new JPanel(new GridLayout(1, 2));
        NextRepaymentPN.add(NextRepayment);
        NextRepaymentPN.add(NextRepaymentValue);

        JTextField StartDateJTF = new JTextField();
        JPanel StartDatePN = createInputPanel("Start Date (YYYY-MM-DD)", StartDateJTF);

        JButton Submit = new JButton("Submit New Contract");
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSubmission(NameJTF, LoanAmountJTF, DurationJTF, loanTypeDropdown, StartDateJTF, NextRepaymentValue);
            }
        });

        // Add components to the frame
        add(NamePN);
        add(LoanAmountPN);
        add(DurationPN);
        add(LoantypePN);
        add(NextRepaymentPN);
        add(StartDatePN);
        add(Submit);
    }

    private JPanel createInputPanel(String label, JTextField textField) {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel(label));
        panel.add(textField);
        return panel;
    }

    private void processSubmission(JTextField nameField, JTextField loanAmountField, JTextField durationField,
                                   JComboBox<String> loanTypeDropdown, JTextField startDateField,
                                   JLabel nextRepaymentValue) {
        String customerName = nameField.getText().trim();
        String loanAmountStr = loanAmountField.getText().trim();
        String durationStr = durationField.getText().trim();
        String startDateStr = startDateField.getText().trim();
        int loanTypeIndex = loanTypeDropdown.getSelectedIndex();

        // Input validation
        if (customerName.isEmpty() || loanAmountStr.isEmpty() || durationStr.isEmpty() || startDateStr.isEmpty() || loanTypeIndex == 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields correctly.");
            return;
        }

        double loanAmount;
        int duration;
        try {
            loanAmount = Double.parseDouble(loanAmountStr);
            duration = Integer.parseInt(durationStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for loan amount and duration.");
            return;
        }

        // Calculate next repayment amount using RepaymentLoan
        double nextRepaymentAmount;
        try {
            nextRepaymentAmount = RepaymentLoan.calculateRepaymentAmount(loanAmount, duration, loanTypeIndex);
            nextRepaymentValue.setText(String.format("%.2f", nextRepaymentAmount));
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        // Calculate next repayment date
        LocalDate startDate;
        try {
            startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        LocalDate nextRepaymentDate = startDate.plusMonths(duration);
        String nextRepaymentDueString = nextRepaymentDate.toString();

        // Create a new Customer object
        Customer newCustomer = new Customer(customerName, "Phone Number Placeholder", loanAmount, duration,
                Double.parseDouble(nextRepaymentValue.getText()), startDateStr,
                nextRepaymentDueString, loanTypeIndex);

        // Add customer to the list and save to file
        customers.add(newCustomer);
        saveCustomersToFile();

        JOptionPane.showMessageDialog(this,
                String.format("Contract created for %s\nNext Repayment Amount: %.2f\nNext Repayment Date: %s",
                        customerName, nextRepaymentAmount, nextRepaymentDueString));
    }

    private void saveCustomersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE, true))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving customers to file: " + e.getMessage());
        }
    }

}
//package Pawn.GUI;
//
//import Pawn.System.Repayment.*;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CreateNewContract extends JFrame {
//    private Map<Integer, RepaymentManager> repaymentCalculators = new HashMap<>();
//
//    public CreateNewContract() {
//
//        repaymentCalculators.put(1, new Jewelry());
//        repaymentCalculators.put(2, new Collectibles());
//        repaymentCalculators.put(3, new Electronic());
//        repaymentCalculators.put(4, new LuxuryItems());
//        repaymentCalculators.put(5, new MusicalInstruments());
//        repaymentCalculators.put(6, new PreciousMetals());
//
//
//        // create Dropdown box
//        JComboBox<String> loanTypeDropdown;
//        // set size for the frame
//        setTitle("Create New Contract");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(700,700);
//        setVisible(true);
//        setLayout(new GridLayout(7,1));
//
//
//        // create Name file for user input
//        JTextField NameJTF = new JTextField("");
//        JLabel NameJL = new JLabel("Customer Name");
//        JPanel NamePN = new JPanel(new GridLayout(1,2));
//        NamePN.add(NameJL);
//        NamePN.add(NameJTF);
//
//        // LaonAmount File for user input
//        JTextField LoanAmountJTF = new JTextField();
//        JLabel LoanAmountLB = new JLabel("Loan Amount");
//        JPanel LoanAmountPN = new JPanel(new GridLayout(1,2));
//        LoanAmountPN.add(LoanAmountLB);
//        LoanAmountPN.add(LoanAmountJTF);
//
//
//
//        // Loan Type Dropdown
//        JLabel loanTypeLabel = new JLabel("Loan Type:");
//        loanTypeDropdown = new JComboBox<>(new String[]{
//                "Select Loan Type",
//                "Jewelry",
//                "Collectibles",
//                "Electronic",
//                "Luxury Items",
//                "Musical Instruments",
//                "Precious Metals"
//        });
//
//        JPanel LoantypePN = new JPanel(new GridLayout(1,2));
//        LoantypePN.add(loanTypeLabel);
//        LoantypePN.add(loanTypeDropdown);
//
//        // create duration text to get user input
//        JTextField DurationJTF = new JTextField();
//        JLabel DurationLB = new JLabel("Pawn Duration");
//        JPanel DurationPN = new JPanel(new GridLayout(1,2));
//
//        DurationPN.add(DurationLB);
//        DurationPN.add(DurationJTF);
//
//
//        // crete automatically text file to display the next repayment of the loan and save it to the contract
//        JLabel NextRepayment = new JLabel("New Repayment ");
//        JLabel NextrepaymentRS = new JLabel();
//        JPanel NextRepaymentPN = new JPanel(new GridLayout(1,2));
//        NextRepaymentPN.add(NextRepayment);
//        NextRepaymentPN.add(NextrepaymentRS);
//
//
//        // Start date
//
//        JLabel StartDateLB = new JLabel("Start Date");
//        JTextField StartDateJTF = new JTextField();
//        StartDateJTF.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // here we have year-month-day
//            }
//        });
//        JPanel StartDatePN = new JPanel(new GridLayout(1,2));
//        StartDatePN.add(StartDateJTF);
//        StartDatePN.add(StartDateLB);
//
//
//
//
//
//        JButton Submit = new JButton("Submit new Contract");
//        Submit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//
//            }
//        });
//
//
//        // add component to frame
//        add(NamePN);
//        add(LoanAmountPN);
//        add(DurationPN);
//        add(LoantypePN);
//        add(NextRepaymentPN);
//        add(Submit);
//
//
//
//
//
//    }
//}
