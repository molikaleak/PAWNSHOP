package Pawn.Class;

public class Repaid_frequency {

    public int repaid_frequency_id;
    private short repaid_frequency;

    public Repaid_frequency(int repaid_frequency_id, short repaid_frequency) {
        this.repaid_frequency_id = repaid_frequency_id;
        this.repaid_frequency = repaid_frequency;
    }

    public short getRepaid_frequency() {
        return repaid_frequency;
    }

    public void setRepaid_frequency(short repaid_frequency) {
        this.repaid_frequency = repaid_frequency;
    }
}
