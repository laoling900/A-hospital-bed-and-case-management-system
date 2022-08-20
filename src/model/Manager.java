package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static controller.LoginController.STAFFID;

public class Manager extends Staff {
    private SqlConnect s = new SqlConnect();
    private ArrayList<DoctorShift> doctorShifts = new ArrayList<>();
    private ArrayList<NurseShift> nurseShifts = new ArrayList<>();

    public Manager(int id,String name,  String password ,String position) {
        super( id, name, password, position);
    }
    public Manager() {
        super();
    }

    //read exist shifts from database
    public void initlizeShifts() {
        try {
            readDoctorShift();
            readNurseShift();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //read doctor shift
    public void readDoctorShift() throws SQLException {

            ResultSet r = s.readDataBase("doctorshift");
            while ((r.next())){
                int shiftID = r.getInt("shiftid");
                int doctorID = r.getInt("doctorid");
                String week   = r.getString("week");
                String time = r.getString("time");
                doctorShifts.add(new DoctorShift(shiftID,doctorID,week,time));
            }

    }

    //read nurst shift
    public void readNurseShift() throws SQLException {

            ResultSet r = s.readDataBase("nurseshift") ;
            while (r.next()){
                int shiftID = r.getInt("shiftid");
                int nurseID =r.getInt("nurseid");
                String week = r.getString("week");
                String time = r.getString("time");
                nurseShifts.add(new NurseShift(shiftID,nurseID,week,time));
            }
    }

    //add a staff to carehome and database
    public void addStaff(AbleCareHome careHome, Staff staff)  {
        //update to careHome stafflist
        careHome.getStaffList().add(staff);
        //add staff to database
        s.addStaff(staff);
        //create a log
        String details = "Manager "+STAFFID + "Add new staff "+staff.getPosition()+" "+staff.getId()+" "+ staff.getName()+" At";
        addAnLog(0, details);
    }

    //create a new patient
    public Patient addPatient(AbleCareHome careHome,String name, String gender,String isolation)  {
        careHome.readPatient();
        ArrayList<Patient> patientList= careHome.getPatientList();
        int nextID = 0;
        if(patientList.isEmpty()==true){
            nextID = 4001;
        }else{
            for(Patient p : patientList){
                if (nextID<p.getId()){
                    nextID =p.getId();
                }
            }
            nextID ++;
        }
        Patient newPatient = new Patient(nextID,name,gender,isolation,null,0);
        return newPatient;
    }

    //check a bed is empty or not
    public boolean isEmptyBed(AbleCareHome careHome,int bedID){
        Bed b = careHome.getBed(bedID);
        if(b.getBedEnum().equals(BedEnum.EMPTY)){
            return true;
        }
        return false;
    }

    //modify a staff information  & update to database
    public void modifyStaff(Staff staff, String newName, String newPassword)  {
        staff.setName(newName);
        staff.setPassword(newPassword);
        s.modifyStaff(staff,newName,newPassword);
        String details = "Manager "+STAFFID+ "Modify staff "+staff.getId()+" Name to: "+staff.getName()+" Password to "+staff.getPassword()+"At ";
        addAnLog(0,details);
    }

    //check a nurse has same day shift or not
    public Boolean checkNurSameDayShift(Nurse n , String day){
        for(NurseShift ns :nurseShifts){
            if(ns.getNurseId() ==n.getId()){
                if(ns.getWeek().equals(day)){
                    return  true;
                }
            }
        }

        return false;
    }

    //check doctor has same day shift or not
    public Boolean checkDocSameDayShift(Doctor doc, String day){
        for(DoctorShift ds : getDoctorShifts()){
            if (ds.getDoctorId() ==doc.getId()) {
                if(ds.getWeek().equals(day)){
                    return true;
                }
            }
        }

        return false;
    }

    //update a doctor shift Memory and database
    public void updateDoctorShift(DoctorShift ds)  {
        doctorShifts.add(ds);
        s.insertDoctorShifts(ds);
        String details = "Manager "+ STAFFID+" Create shift "+ds.toString()+ " At ";
        addAnLog(0, details);
    }

    //update a nurse shift Memory and database
    public void updateNurShift(NurseShift ns)  {
        nurseShifts.add(ns);
        s.insertNurseShifts(ns);
        String details = "Manager "+ STAFFID+" Create shift "+ns.toString()+ " At ";
        addAnLog(0, details);
    }

    //delete a doctor shift Memory and database
    public void removeDoctorShift(DoctorShift ds)  {
        doctorShifts.remove(ds);
        s.useIntDeleteInformation("doctorshift","shiftid",ds.getShiftId());
        String details = "Manager "+ STAFFID+ " Remove shift " + ds.toString()+" At ";
        addAnLog(0,details);
    }

    //delete a nurse shift Memory and database
    public void removeNurShift(NurseShift ns)  {
        nurseShifts.remove(ns);
        s.useIntDeleteInformation("nurseshift", "shiftid", ns.getShiftId());
        String details = "Manager "+ STAFFID+ " Remove shift " + ns.toString()+" At ";
        addAnLog(0,details);
    }

    //validation check Method that Manager can not create a user name included special characters
    public boolean checkString(String str) {
        try {
            String regEx = "[ `!@#$%^&*()+=|{}’:;’,\r\n" + ".<>/?！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\\n|\\r|\\t";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            if (m.find()) {
                System.err.print("Wrong enter, pls enter characters only: ");
                throw new SpecialCharactersException("Name");
            } else
                return true;
        } catch (Exception e) {
        }
        return false;
    }


    public void addNewPatientLog(Patient p){
        String details = "Manager "+STAFFID+ " Add new patient " +p.getId()+" "+p.getName() +" to the system At ";
        addAnLog(p.getId(), details);
    }

    public void giveNewPatientBedLog(Patient p){
        String details = "Manager "+STAFFID+ "Give new patient "+p.getId()+" the bed "+ p.getBedId() +" At";
        addAnLog(p.getId(), details);
    }

    public void addAnLog(int patientID, String details){
        LogRecord logRecord= new LogRecord(patientID, STAFFID, details);
        s.recordAnLog(logRecord);
    }


    //getting and setting Methods
    public ArrayList<DoctorShift> getDoctorShifts() {
        return doctorShifts;
    }

    public void setDoctorShifts(ArrayList<DoctorShift> doctorShifts) {
        this.doctorShifts = doctorShifts;
    }

    public ArrayList<NurseShift> getNurseShifts() {
        return nurseShifts;
    }

    public void setNurseShifts(ArrayList<NurseShift> nurseShifts) {
        this.nurseShifts = nurseShifts;
    }

    public SqlConnect getS() {
        return s;
    }

    public void setS(SqlConnect s) {
        this.s = s;
    }
}
