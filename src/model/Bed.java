package model;

public class Bed<BedEnun> {
    private int bID;
    private BedEnun bedEnum;

    public Bed(int bID, BedEnun bedEnum) {
        this.bID = bID;
        this.bedEnum = bedEnum;
    }

    //All getting and setting methods
    public int getbID() {
        return bID;
    }

    public void setbID(int bID) {
        this.bID = bID;
    }

    public BedEnun getBedEnum() {
        return bedEnum;
    }

    public void setBedEnum(BedEnun bedEnum) {
        this.bedEnum = bedEnum;
    }
}
