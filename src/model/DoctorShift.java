package model;


public class DoctorShift {
    private int shiftId ;
    private int doctorId;
    private String week;
    private String time;

    public DoctorShift(int shiftId, int doctorId, String week, String time) {
        this.shiftId = shiftId;
        this.doctorId = doctorId;
        this.week = week;
        this.time = time;
    }

    @Override
    public String toString(){
        return "Shift "+shiftId+" for Doctor "+doctorId+ " in "+ week+ " " +time;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


