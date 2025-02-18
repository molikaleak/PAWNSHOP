package Pawn.Class;

import Pawn.System.Calculator.LoanCalculator;
import Pawn.System.Repayment.RepaymentManager;

public abstract class Loan {
    protected double amount;
    protected int term; // in years
    protected LoanCalculator calculator;


    public abstract void applyForLoan(double amount, int term);
    public abstract void displayLoanDetails();

    public void setCalculator(LoanCalculator calculator) {
        this.calculator = calculator;
    }

    public static class Jewelry extends Loan {
        @Override
        public void applyForLoan(double amount, int term) {
            this.amount = amount;
            this.term = term;
            System.out.println("Jewelry loan application submitted for: " + amount);
        }

        @Override
        public void displayLoanDetails() {
            double interest = calculator.calculate(amount, term);
            double totalRepayment = amount + interest;
            double monthlyRepayment = totalRepayment / (term * 12); // Monthly repayment calculation
            System.out.println("Loan Type: Jewelry");
            System.out.println("Amount: " + amount);
            System.out.println("Term: " + term + " years");
            System.out.println("Interest: " + interest);
            System.out.println("Total Repayment: " + totalRepayment);
            System.out.println("Monthly Repayment: " + monthlyRepayment);
            System.out.println("Interest Rate: " + (calculator.getInterestRate() * 100) + "%");
        }
    }

    public static class Collectibles extends Loan {
        @Override
        public void applyForLoan(double amount, int term) {
            this.amount = amount;
            this.term = term;
            System.out.println("Collectibles loan application submitted for: " + amount);
        }

        @Override
        public void displayLoanDetails() {
            double interest = calculator.calculate(amount, term);
            double totalRepayment = amount + interest;
            double monthlyRepayment = totalRepayment / (term * 12);
            System.out.println("Loan Type: Collectibles");
            System.out.println("Amount: " + amount);
            System.out.println("Term: " + term + " years");
            System.out.println("Interest: " + interest);
            System.out.println("Total Repayment: " + totalRepayment);
            System.out.println("Monthly Repayment: " + monthlyRepayment);
            System.out.println("Interest Rate: " + (calculator.getInterestRate() * 100) + "%");
        }
    }

    public static class Electronic extends Loan {
        @Override
        public void applyForLoan(double amount, int term) {
            this.amount = amount;
            this.term = term;
            System.out.println("Electronic loan application submitted for: " + amount);
        }

        @Override
        public void displayLoanDetails() {
            double interest = calculator.calculate(amount, term);
            double totalRepayment = amount + interest;
            double monthlyRepayment = totalRepayment / (term * 12);
            System.out.println("Loan Type: Electronic");
            System.out.println("Amount: " + amount);
            System.out.println("Term: " + term + " years");
            System.out.println("Interest: " + interest);
            System.out.println("Total Repayment: " + totalRepayment);
            System.out.println("Monthly Repayment: " + monthlyRepayment);
            System.out.println("Interest Rate: " + (calculator.getInterestRate() * 100) + "%");
        }
    }

    public static class MusicalInstruments extends Loan {
        @Override
        public void applyForLoan(double amount, int term) {
            this.amount = amount;
            this.term = term;
            System.out.println("Musical Instruments loan application submitted for: " + amount);
        }

        @Override
        public void displayLoanDetails() {
            double interest = calculator.calculate(amount, term);
            double totalRepayment = amount + interest;
            double monthlyRepayment = totalRepayment / (term * 12);
            System.out.println("Loan Type: Musical Instruments");
            System.out.println("Amount: " + amount);
            System.out.println("Term: " + term + " years");
            System.out.println("Interest: " + interest);
            System.out.println("Total Repayment: " + totalRepayment);
            System.out.println("Monthly Repayment: " + monthlyRepayment);
            System.out.println("Interest Rate: " + (calculator.getInterestRate() * 100) + "%");
        }
    }

    public static class LuxuryItems extends Loan {
        @Override
        public void applyForLoan(double amount, int term) {
            this.amount = amount;
            this.term = term;
            System.out.println("Luxury Items loan application submitted for: " + amount);
        }

        @Override
        public void displayLoanDetails() {
            double interest = calculator.calculate(amount, term);
            double totalRepayment = amount + interest;
            double monthlyRepayment = totalRepayment / (term * 12);
            System.out.println("Loan Type: Luxury Items");
            System.out.println("Amount: " + amount);
            System.out.println("Term: " + term + " years");
            System.out.println("Interest: " + interest);
            System.out.println("Total Repayment: " + totalRepayment);
            System.out.println("Monthly Repayment: " + monthlyRepayment);
            System.out.println("Interest Rate: " + (calculator.getInterestRate() * 100) + "%");
        }
    }

    public static class PreciousMetals extends Loan {
        @Override
        public void applyForLoan(double amount, int term) {
            this.amount = amount;
            this.term = term;
            System.out.println("Precious Metals loan application submitted for: " + amount);
        }

        @Override
        public void displayLoanDetails() {
            double interest = calculator.calculate(amount, term);
            double totalRepayment = amount + interest;
            double monthlyRepayment = totalRepayment / (term * 12);
            System.out.println("Loan Type: Precious Metals");
            System.out.println("Amount: " + amount);
            System.out.println("Term: " + term + " years");
            System.out.println("Interest: " + interest);
            System.out.println("Total Repayment: " + totalRepayment);
            System.out.println("Monthly Repayment: " + monthlyRepayment);
            System.out.println("Interest Rate: " + (calculator.getInterestRate() * 100) + "%");
        }
    }
}