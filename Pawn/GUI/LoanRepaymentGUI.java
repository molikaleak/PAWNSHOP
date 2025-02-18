package Pawn.GUI;

import Pawn.System.Repayment.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoanRepaymentGUI extends JFrame {
    private JTextField loanAmountField;
    private JTextField durationField;
    private JComboBox<String> loanTypeDropdown;
    private JLabel resultLabel;

    private Map<Integer, RepaymentManager> repaymentCalculators = new HashMap<>();

    public LoanRepaymentGUI() {
        // Initialize the repayment calculators for different loan types
        repaymentCalculators.put(1, new Jewelry());
        repaymentCalculators.put(2, new Collectibles());
        repaymentCalculators.put(3, new Electronic());
        repaymentCalculators.put(4, new LuxuryItems());
        repaymentCalculators.put(5, new MusicalInstruments());
        repaymentCalculators.put(6, new PreciousMetals());

        setTitle("Loan Repayment Calculator");
        setBounds(150,200,400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2)); // 6 rows, 2 columns

        // Loan Amount Field
        JLabel amountLabel = new JLabel("Loan Amount:");
        loanAmountField = new JTextField();
        add(amountLabel);
        add(loanAmountField);

        // Loan Duration Field
        JLabel durationLabel = new JLabel("Duration (months):");
        durationField = new JTextField();
        add(durationLabel);
        add(durationField);

        // Loan Type Dropdown
        JLabel loanTypeLabel = new JLabel("Loan Type:");
        loanTypeDropdown = new JComboBox<>(new String[]{
                "Select Loan Type",
                "Jewelry",
                "Collectibles",
                "Electronic",
                "Luxury Items",
                "Musical Instruments",
                "Precious Metals"
        });
        add(loanTypeLabel);
        add(loanTypeDropdown);

        // Calculate Button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        add(calculateButton);

        // Result Label
        resultLabel = new JLabel("");
        add(resultLabel);

        // Back/Exit Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window
                dispose(); // This will close the GUI window
            }
        });
        add(backButton); // Add the button to the layout

        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double loanAmount = Double.parseDouble(loanAmountField.getText());
                int duration = Integer.parseInt(durationField.getText());
                int loanTypeIndex = loanTypeDropdown.getSelectedIndex();

                if (loanTypeIndex == 0) {
                    resultLabel.setText("Please select a loan type.");
                    return;
                }

                // Use the provided method to calculate the repayment amount
                double monthlyRepayment = calculateRepaymentAmount(loanAmount, duration, loanTypeIndex);

                // Show result
                String result = String.format("Monthly Repayment: %.2f", monthlyRepayment);
                resultLabel.setText(result);

            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid number format. Please try again.");
            } catch (IllegalArgumentException ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        }
    }

    // Method to calculate repayment amount based on loan amount, duration, and loan type ID
    public double calculateRepaymentAmount(double loanAmount, int duration, int loanTypeId) {
        RepaymentManager calculator = repaymentCalculators.get(loanTypeId);
        if (calculator != null) {
            return calculator.repaymentCalculator(loanAmount, duration);
        } else {
            throw new IllegalArgumentException("Invalid loan type ID. Cannot calculate repayment amount.");
        }
    }
}