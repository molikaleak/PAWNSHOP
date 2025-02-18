package Pawn.Database;

import java.sql.*;

public class DatabaseConnection {

    // Database credentials
    private static final String JDBC_URL = "jdbc:mysql://localhost:8889/pawnshop_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    // Static method to initialize or return the connection
    public static Connection getConnection() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                System.out.println("Database connection established.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static boolean executeSelect(String query) {
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            boolean hasResults = false;

            while (resultSet.next()) {
                hasResults = true;
                // Modify based on your columns
                String contract_id = resultSet.getString("contract_id");
                String emp_id = resultSet.getString("emp_id");

                // Print results
                System.out.println("contract_id: " + contract_id + ", emp_id: " + emp_id);
            }

            return hasResults;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to execute an INSERT/UPDATE/DELETE query
    public static boolean executeUpdate(String query) {
        try (Statement statement = getConnection().createStatement()) {

            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Query executed successfully. Rows affected: " + rowsAffected);
                return true;
            } else {
                System.out.println("Query executed but no rows were affected.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Static method to close the connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null; // Reset connection to allow re-initialization if needed
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
