package Pawn.Class;

public class Late_fee {

    public int late_fee_id;
    private int percent;

    public Late_fee(int late_fee_id, int percent) {
        this.late_fee_id = late_fee_id;
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
