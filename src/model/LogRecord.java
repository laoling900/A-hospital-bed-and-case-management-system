package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class LogRecord {
    private int patientID;
    private int staffID;
    private String details;
    private Date time;
    private File f;
    private SqlConnect s = new SqlConnect();



    public LogRecord(int patientID, int staffID, String details) {
        this.patientID = patientID;
        this.staffID = staffID;
        this.details = details;
        this.time = new Date();
    }

    public LogRecord() {
    }

    //When close system , save all Log to a file
    public void saveAllLogToFile() throws SQLException, IOException {
        //create a file first
        this.f = new File("log");
        if(!f.exists()){
            f.createNewFile();
        }
        PrintWriter pr = new PrintWriter(new FileWriter(f, false));
        //read from database and save to file
        ResultSet r = null;
        r = s.readDataBase("log");

        while (r.next()){
            int patientID = r.getInt("patientid");
            int staffID = r.getInt("staffid");
            String detail = r.getString("details");
            String time = r.getString("time");
            pr.write(patientID+" "+ staffID+" "+detail+""+ time+"\n");
        }
        pr.close();
    }

    //When A patient discharge from care home ,give all the log information in one file
    public void saveAPatientLog(int patientID) throws IOException, SQLException {
        this.f = new File( String.valueOf(patientID)+" All log");
        if(! f.exists()){
            f.createNewFile();
        }
        PrintWriter pr = new PrintWriter(new FileWriter(f, true));
        //read from database and save only this patientID's information
        ResultSet r = s.readDataBase("log");
        while (r.next()){
            int pID =r.getInt("patientid");
            if(patientID == pID){
                int staffID = r.getInt("staffid");
                String detail = r.getString("details");
                String time = r.getString("time");
                pr.write(patientID+" "+ staffID+" "+detail+""+ time+"\n");
            }
        }
        pr.close();

    }

    @Override
    public String toString(){
        return "PatientID: "+patientID+" StaffID: "+ staffID+ " Time: "+time+" Details: "+details;
    }


    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }
}

