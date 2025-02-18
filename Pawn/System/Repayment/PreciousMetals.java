package Pawn.System.Repayment;

public class PreciousMetals implements RepaymentManager {

    @Override
    public String toString() {
        return "PreciousMetals"; // Return the name of the loan type
    }
    // Override method from interface to implement repayment calculation
    @Override
    public double repaymentCalculator(double loan_amount, int duration) {
        // Validate inputs
        if (loan_amount <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive.");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive integer.");
        }

        // Annual interest rate (12%)
        double annualRate = 0.12/12;

        // Calculate monthly repayment using the formula
        return (annualRate * loan_amount * Math.pow(1 + annualRate, duration))
                / (Math.pow(1 + annualRate, duration) - 1);
    }
}