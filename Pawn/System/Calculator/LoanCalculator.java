package Pawn.System.Calculator;

public interface LoanCalculator {
    double calculate(double amount, int term);
    double getInterestRate(); // New method to get the interest rate
}

// Calculator Implementations
class Jewelry implements LoanCalculator {
    private final double interestRate = 0.1; // 10%

    @Override
    public double calculate(double amount, int term) {
        return amount * interestRate * term;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}

class MusicalInstruments implements LoanCalculator {
    private final double interestRate = 0.15; // 15%

    @Override
    public double calculate(double amount, int term) {
        return amount * interestRate * term;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}

class Electronic implements LoanCalculator {
    private final double interestRate = 0.2; // 20%

    @Override
    public double calculate(double amount, int term) {
        return amount * interestRate * term;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}

class PreciousMetals implements LoanCalculator {
    private final double interestRate = 0.2; // 20%

    @Override
    public double calculate(double amount, int term) {
        return amount * interestRate * term;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}

class Collectibles implements LoanCalculator {
    private final double interestRate = 0.12; // 12%

    @Override
    public double calculate(double amount, int term) {
        return amount * interestRate * term;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}

class LuxuryItems implements LoanCalculator {
    private final double interestRate = 0.18; // 18%

    @Override
    public double calculate(double amount, int term) {
        return amount * interestRate * term;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}