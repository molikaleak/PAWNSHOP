package Pawn.Class;

public class Penalty_closing {

    public int penalty_closing_id;
    private int percent;

    public Penalty_closing(int penalty_closing_id, int percent) {
        this.penalty_closing_id = penalty_closing_id;
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
