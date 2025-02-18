//package Pawn.Database;
//
//public class ExecuteDatabase {
//
//    public static void main(String[] args){
//        DatabaseConnection.getConnection();
//
//        String selectQuery = "SELECT contract_id, customer_id, prop_id, electronic_sign_id, emp_id, " +
//                "contract_signdate, contract_description, reference_doc, currency_id, " +
//                "loan_amount, interest_rate_id, late_fee_id, penalty_closing_id, " +
//                "repaid_frequency_id, start_paid_date, duration, status " +
//                "FROM contract WHERE status = 'pending'";
//
//        boolean selectSuccess = DatabaseConnection.executeSelect(selectQuery);
//        System.out.println("SELECT query successful: " + selectSuccess);
//
//        DatabaseConnection.closeConnection();
//
//    }
//}