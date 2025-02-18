package Pawn.Class;

import java.io.File;

public class Property {

    private int propId;
    private String propType;
    private short estimatePrice;
    private short realPrice;
    private String forFeiture;
    private String propCondition;
    private File propImg;
    private String numberOfTimesPawn;


    public Property(int propId, String propType, short estimatePrice, short realPrice, String forFeiture, String propCondition, File propImg, String numberOfTimesPawn) {
        this.propId = propId;
        this.propType = propType;
        this.estimatePrice = estimatePrice;
        this.realPrice = realPrice;
        this.forFeiture = forFeiture;
        this.propCondition = propCondition;
        this.propImg = propImg;
        this.numberOfTimesPawn = numberOfTimesPawn;
    }
    // this constructor were created for user to calculate the real_price of property and the estimate price in the market
    public Property(int propId, short realPrice , short estimatePrice) {
        this.realPrice = realPrice;
        this.propId = propId;
        this.estimatePrice = estimatePrice;
    }

    public String getPropType() {
        return propType;
    }
    public String propDetail(){
        return "Property : "+ propType + ", Image : " + propImg;
    }

    public void setPropType(String propType) {
        this.propType = propType;
    }

    public short getEstimatePrice() {
        return estimatePrice;
    }

    public void setEstimatePrice(short estimatePrice) {
        this.estimatePrice = estimatePrice;
    }

    public short getRealPriceealPrice() {
        return realPrice;
    }

    public void setReal_price(short realPrice) {
        this.realPrice = realPrice;
    }

    public String getForFeiture() {
        return forFeiture;
    }

    public void setForFeiture(String forFeiture) {
        this.forFeiture = forFeiture;
    }

    public String getPropCondition() {
        return propCondition;
    }

    public void setPropCondition(String propCondition) {
        this.propCondition = propCondition;
    }

    public File getPropImg() {
        return propImg;
    }

    public void setPropImg(File propImg) {
        this.propImg = propImg;
    }

    public String getNumberOfTimesPawn() {
        return numberOfTimesPawn;
    }

    public void setNumberOfTimesPawn(String numberOfTimesPawn) {
        this.numberOfTimesPawn = numberOfTimesPawn;
    }
}
