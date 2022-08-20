package model;

public class NurseShift {
    private int shiftId;
    private int nurseId;
    private String week;
    private String time;

    public NurseShift(int shiftId, int nurseId, String week, String time) {
        this.shiftId = shiftId;
        this.nurseId = nurseId;
        this.week = week;
        this.time = time;
    }

    @Override
    public String toString(){
        return "Shift "+shiftId+" for Nurse "+nurseId+ " in "+ week+ " " +time;
    }

    //all getting and setting method
    public int getShiftId() {
        return shiftId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public String getWeek() {
        return week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
