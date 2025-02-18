package Pawn.Class;

public class Interest_rate {

    public int Interest_rate_id;
    private int percent;

    public Interest_rate(int interest_rate_id, int percent) {
        this.Interest_rate_id = interest_rate_id;
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
