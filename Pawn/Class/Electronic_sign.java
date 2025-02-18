package Pawn.Class;

import java.io.File;
import java.sql.Timestamp;

public class Electronic_sign {

    public int electronic_sign_id;
    private Timestamp signed_datetime;
    private File pawnshop_sign;

    public Electronic_sign(int electronic_sign_id, Timestamp signed_datetime, File pawnshop_sign) {
        this.electronic_sign_id = electronic_sign_id;
        this.signed_datetime = signed_datetime;
        this.pawnshop_sign = pawnshop_sign;
    }

    public Timestamp getSigned_datetime() {
        return signed_datetime;
    }

    public void setSigned_datetime(Timestamp signed_datetime) {
        this.signed_datetime = signed_datetime;
    }

    public File getPawnshop_sign() {
        return pawnshop_sign;
    }

    public void setPawnshop_sign(File pawnshop_sign) {
        this.pawnshop_sign = pawnshop_sign;
    }
}
