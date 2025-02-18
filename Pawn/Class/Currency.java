package Pawn.Class;

public class Currency {

    public int currency_id;
    private int currency;

    public Currency(int currency_id, int currency) {
        this.currency_id = currency_id;
        this.currency = currency;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}
