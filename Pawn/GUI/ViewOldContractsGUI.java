package Pawn.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ViewOldContractsGUI extends JFrame {
    private JTextArea contractsArea;
    private static final String CONTRACT_FILE = "contractdata.txt";

    public ViewOldContractsGUI() {
        setTitle("Old Contracts");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        contractsArea = new JTextArea();
        contractsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contractsArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        loadContracts();
        setVisible(true);
    }

    private void loadContracts() {
        Path filePath = Path.of(CONTRACT_FILE);
        try {
            List<String> contracts = Files.readAllLines(filePath);
            if (contracts.isEmpty()) {
                contractsArea.setText("No old contracts found.");
            } else {
                StringBuilder contractsList = new StringBuilder("Old Contracts:\n");
                for (String contract : contracts) {
                    contractsList.append(contract).append("\n");
                }
                contractsArea.setText(contractsList.toString());
            }
        } catch (IOException e) {
            contractsArea.setText("Error reading contracts file: " + e.getMessage());
        }
    }

}
