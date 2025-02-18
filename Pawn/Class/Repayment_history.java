package Pawn.Class;

import java.sql.Timestamp;

public class Repayment_history {

    private double penaltyfee_amount;
    private Timestamp history_date;
    private short monthnumber_payoff;
    private Timestamp late_day;

    public Repayment_history(double penaltyfee_amount, Timestamp history_date, short monthnumber_payoff, Timestamp late_day) {
        this.penaltyfee_amount = penaltyfee_amount;
        this.history_date = history_date;
        this.monthnumber_payoff = monthnumber_payoff;
        this.late_day = late_day;
    }

    public double getPenaltyfee_amount() {
        return penaltyfee_amount;
    }

    public void setPenaltyfee_amount(double penaltyfee_amount) {
        this.penaltyfee_amount = penaltyfee_amount;
    }

    public Timestamp getHistory_date() {
        return history_date;
    }

    public void setHistory_date(Timestamp history_date) {
        this.history_date = history_date;
    }

    public short getMonthnumber_payoff() {
        return monthnumber_payoff;
    }

    public void setMonthnumber_payoff(short monthnumber_payoff) {
        this.monthnumber_payoff = monthnumber_payoff;
    }

    public Timestamp getLate_day() {
        return late_day;
    }

    public void setLate_day(Timestamp late_day) {
        this.late_day = late_day;
    }


}
