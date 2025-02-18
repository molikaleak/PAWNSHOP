package Pawn.Class;

import Pawn.System.Repayment.RepaymentManager;

import java.io.File;
import java.sql.Timestamp;

public class Contract {

    private int contract_id ;
    public int customer_id;
    public int prop_id;
    public int electronic_sign_id;
    public int emp_id;
    private int contract_signdate;
    private String contract_description;
    private File reference_doc;
    public int currency_id;
    public double loan_amount;
    public int interest_rate_id;
    public int late_fee_id;
    public int penalty_closing_id;
    public int repaid_frequency_id;
    private int start_paid_date;
    public int duration;
    private String status;
    protected RepaymentManager repaymentManager;

    public Contract(String name, double loanAmount, int duration, double monthlyPayment, String contractId) {
    }

    public void contract(int contract_id, int customer_id, int prop_id, int electronic_sign_id, int emp_id, int contract_signdate, String contract_description, File reference_doc, int currency_id, double loan_amount, int interest_rate_id, int late_fee_id, int penalty_closing_id, int repaid_frequency_id, int start_paid_date, int duration, String status) {
        this.contract_id = contract_id;
        this.customer_id = customer_id;
        this.prop_id = prop_id;
        this.electronic_sign_id = electronic_sign_id;
        this.emp_id = emp_id;
        this.contract_signdate = contract_signdate;
        this.contract_description = contract_description;
        this.reference_doc = reference_doc;
        this.currency_id = currency_id;
        this.loan_amount = loan_amount;
        this.interest_rate_id = interest_rate_id;
        this.late_fee_id = late_fee_id;
        this.penalty_closing_id = penalty_closing_id;
        this.repaid_frequency_id = repaid_frequency_id;
        this.start_paid_date = start_paid_date;
        this.duration = duration;
        this.status = status;
    }

    // I create this constructor for calculate repayment management

    public Contract(double loan_amount, int duration) {
        this.loan_amount = loan_amount;
        this.duration = duration;
    }





    public int getContract_signdate() {
        return contract_signdate;
    }

    public void setContract_signdate(int contract_signdate) {
        this.contract_signdate = contract_signdate;
    }

    public String getContract_description() {
        return contract_description;
    }

    public void setContract_description(String contract_description) {
        this.contract_description = contract_description;
    }

    public File getReference_doc() {
        return reference_doc;
    }

    public void setReference_doc(File reference_doc) {
        this.reference_doc = reference_doc;
    }

    public double getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public int getStart_paid_date() {
        return start_paid_date;
    }

    public void setStart_paid_date(int start_paid_date) {
        this.start_paid_date = start_paid_date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
