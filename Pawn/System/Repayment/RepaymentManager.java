package Pawn.System.Repayment;

// for all loan type apply with the same formula but different annual rate
@FunctionalInterface
public interface RepaymentManager {
    public double repaymentCalculator(double loan_amount, int duration );
}



