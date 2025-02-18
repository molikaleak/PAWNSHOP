package Pawn.GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ReportGeneratorGUI extends JFrame {
    private JTextArea reportArea;
    private JButton generateReportButton;
    private static final String CONTRACT_FILE = "contractdata.txt";

    public ReportGeneratorGUI() {
        setTitle("Report Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        add(scrollPane, BorderLayout.CENTER);

        generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(new GenerateReportListener());
        add(generateReportButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class GenerateReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            generateReport();
        }
    }

    private void generateReport() {
        reportArea.setText("Generating Report...\n");

        int totalContracts = 0;
        double totalAmountPawned = 0;
        List<Integer> loanTypeIds = new ArrayList<>();

        Path contractFilePath = Path.of(CONTRACT_FILE);
        try {
            List<String> contracts = Files.readAllLines(contractFilePath);
            totalContracts = contracts.size();

            for (String contract : contracts) {
                // Parse the contract string to extract data for calculations
                String[] parts = contract.split(", ");
                if (parts.length < 7) continue; // Ensure there are enough parts

                try {
                    // Extract loan amount
                    double loanAmount = Double.parseDouble(parts[2].split(": ")[1]);
                    totalAmountPawned += loanAmount;

                    // Extract loan type ID
                    int loanTypeId = Integer.parseInt(parts[7].split(": ")[1]); // Adjust index for loan type ID
                    loanTypeIds.add(loanTypeId);
                } catch (NumberFormatException ex) {
                    reportArea.append("Error parsing contract data: " + ex.getMessage() + "\n");
                }
            }

            double averagePawnAmount = totalContracts == 0 ? 0 : totalAmountPawned / totalContracts;

            reportArea.append(String.format("Total Contracts: %d%n", totalContracts));
            reportArea.append(String.format("Total Amount Pawned: %.2f%n", totalAmountPawned));
            reportArea.append(String.format("Average Pawn Amount: %.2f%n", averagePawnAmount));

            // Calculate the most popular loan type ID
            if (!loanTypeIds.isEmpty()) {
                Map<Integer, Long> loanTypeCount = loanTypeIds.stream()
                        .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

                Optional<Map.Entry<Integer, Long>> mostPopular = loanTypeCount.entrySet().stream()
                        .max(Map.Entry.comparingByValue());

                if (mostPopular.isPresent()) {
                    int loanTypeId = mostPopular.get().getKey();
                    long count = mostPopular.get().getValue();
                    String loanTypeName = ""; // Placeholder for loan type name
                    // Assuming you have a method to get loan type name by ID
                    loanTypeName = getLoanTypeName(loanTypeId); // Implement this method as needed

                    reportArea.append(String.format("Most Popular Loan Type: %s (ID: %d), Count: %d%n",
                            loanTypeName != null ? loanTypeName : "Unknown", loanTypeId, count));
                }
            } else {
                reportArea.append("No loan types were recorded.\n");
            }
        } catch (IOException ex) {
            reportArea.append("Error generating report: " + ex.getMessage() + "\n");
        }
    }

    // Dummy method for loan type name retrieval
    private String getLoanTypeName(int loanTypeId) {
        // Replace this with your actual logic to retrieve loan type names
        switch (loanTypeId) {
            case 1: return "Jewelry";
            case 2: return "Collectibles";
            case 3: return "Electronics";
            case 4: return "Luxury Items";
            case 5: return "Musical Instruments";
            case 6: return "Precious Metals";
            default: return "Unknown";
        }
    }

}
