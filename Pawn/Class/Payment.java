package Pawn.Class;

import java.sql.Timestamp;

public class Payment {

    public int payment_id;
    public int history_id;
    public int currency_id;
    private Timestamp payment_date;
    private String payment_method;
    private double payment_amount;
    private double exchange_rate;

    public Payment(int payment_id, int history_id, int currency_id, Timestamp payment_date, String payment_method, double payment_amount, double exchange_rate) {
        this.payment_id = payment_id;
        this.history_id = history_id;
        this.currency_id = currency_id;
        this.payment_date = payment_date;
        this.payment_method = payment_method;
        this.payment_amount = payment_amount;
        this.exchange_rate = exchange_rate;
    }

    public Timestamp getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Timestamp payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public double getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
}
