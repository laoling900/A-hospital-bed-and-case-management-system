package model;

public class Medicine {

    private String  medName;
    private int time;
    private int amount;
    private String unit;

    public Medicine(String medName, int time, int amount, String unit) {
        this.medName = medName;
        this.time = time;
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return medName   + "(" + amount + unit + ")" ;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
