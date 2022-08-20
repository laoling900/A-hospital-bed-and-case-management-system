package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Able care home all entity are save here
public class AbleCareHome {
    private SqlConnect s = new SqlConnect();
    private Ward[]  ward = new Ward[]{new Ward("w1"),new Ward("w2")};  //2 wards  included 12 rooms , 38 beds are all save here
    private ArrayList<Patient> patientList = new ArrayList<>();  //patients are here
    private ArrayList<Patient> noBedPatientsList = new ArrayList<>();
    private ArrayList<Staff> staffList = new ArrayList<>();  //manager, doctor, nurse are here


    public void initlizeCareHome()  {
        readWard();
        readPatient();
        readStaff();
    }

    //Read bed information from database
    public void readWard()  {
        ResultSet r = null;
        try {
            r = s.readDataBase("ward");
            if(r==null){
                // if database don't have any ward/room/bed information , create these information
                s.initlizeWard(ward);
            }
            //else read the bed information from database
            while (r.next()){
                int bedID = r.getInt("bedid");
                String bedEnum = r.getString("bedenum");
                if(bedEnum.equals("MALE")){
                    //change bed statue
                    Bed b = getBed(bedID);
                    b.setBedEnum(BedEnum.MALE);

                }else if(bedEnum.equals("FEMALE")){
                    Bed b = getBed(bedID);
                    b.setBedEnum(BedEnum.FEMALE);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Read patient information from database
    public void readPatient(){
        ResultSet r = null;
        try {
            r = s.readDataBase("patient");

            while (r.next()){
                int id =r.getInt("id");
                String name = r.getString("name");
                String gender = r.getString("gender");
                String needIsolation = r.getString("needIsolation");
                String prescriptions = r.getString("prescriptions");
                int bedId  = r.getInt("bedid");
                Patient p = new Patient(id,name,gender,needIsolation,prescriptions,bedId);
                patientList.add(p);
                if (bedId ==0){
                    noBedPatientsList.add(p);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Read staff information from database
    public void readStaff() {
        try {
            ResultSet r = s.readDataBase("staff");
            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                String password = r.getString("password");
                String position = r.getString("position");
                if (position.equals("DOCTOR")) {
                    staffList.add(new Doctor(id, name, password, "DOCTOR"));
                }else if(position.equals("NURSE")){
                    staffList.add(new Nurse(id, name, password,"NURSE"));
                }else if(position.equals("MANAGER")){
                    staffList.add(new Manager(id, name, password, "MANAGER"));
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Add new patient to the database
    public void addNewPatient(Patient p) {
        patientList.add(p);
        noBedPatientsList.add(p);
        //update to database
        s.addPatientToSql(p);
    }

    //give patient a bed
    public void givePatientABed(Patient p , int bedID,BedEnum bedEnum){
        p.setBedId(bedID);
        getBed(bedID).setBedEnum(bedEnum);
        removePatientFromNoBedList(p);
        //connect database
        s.modifyPatientBed(p, bedID, p.getGender());
    }

    //remove patient from no bed list after give bed to them
    public void removePatientFromNoBedList(Patient p){
        if(noBedPatientsList.contains(p)){
            noBedPatientsList.remove(p);
        }
    }

    //get a patient from arraylist
    public Patient getPatient(int pID){
        Patient p = null;
        for(Patient patient :patientList){
            if(pID == patient.getId()){
                p = patient;
                return p;
            }
        }
        return null;
    }

    //get a patient information use bedId
    public Patient getPatientbyBedID(int bedID) throws NoPatientException {
        for (Patient p : patientList) {
            if (p.getBedId() == bedID) {
                return p;
            }
        }
        throw new NoPatientException();

    }

    //get bed use bedID
    public Bed getBed(int bID){
        Bed bed = null;
        for(Ward w: ward ){
            for(Room r: w.getRoomMap().values()){
                for(Bed b: r.getBedMap().values()){
                    if(bID == b.getbID()){
                        bed =b;
                        return bed;
                    }
                }
            }
        }
        return  null;
    }

    //checkroom gender match the patient wants to come
    public boolean checkRoomGender(int bID, String wantGender ){
        boolean hasEmptyBed =false;
        boolean hasMaleBed = false;
        boolean hasFemaleBed = false;

        for(Ward w: ward ) {
            for (Room r : w.getRoomMap().values()) {
                if(r.getBedMap().containsKey(bID)){
                    for(Bed b:r.getBedMap().values()){
                        if(b.getBedEnum().equals(BedEnum.EMPTY)){
                            hasEmptyBed =true;
                        }else if(b.getBedEnum().equals(BedEnum.MALE)){
                            hasMaleBed = true;
                        }else if(b.getBedEnum().equals(BedEnum.FEMALE)){
                            hasFemaleBed =true;
                        }
                    }
                }
            }
        }
        //if all genders in room are EMPTY, return true
        if(hasMaleBed ==false && hasFemaleBed ==false &&hasEmptyBed ==true){
            return  true;
        //else if a gender match want gender, return true
        }else if(wantGender.equals("MALE") && hasMaleBed ==true &&hasEmptyBed ==true){
            return  true;
        }else if(wantGender.equals("FEMALE") && hasFemaleBed ==true && hasEmptyBed ==true){
            return  true;
        }
        return false;
    }

    //check if the patient can move to a new bed or not
    public boolean movePatientToNewBed(Patient p , int newBedID)  {
        if (!getBed(newBedID).getBedEnum().equals(BedEnum.EMPTY)){
            return false;
        }else if(checkRoomGender(newBedID,p.getGender())){
            return  true;
        }
        return false;
    }

    //remove patient from a bed
    public void removePatientFromBed(Patient p, int bedID)  {
        //change the hashmap/arraylist
        getBed(bedID).setBedEnum(BedEnum.EMPTY);
        p.setBedId(0);
        //change the database
        s.modifyPatientBed(p,bedID, BedEnum.EMPTY.toString());

    }

    //remove a patient from system
    public void removePatient(Patient p){
        removePatientFromBed(p, p.getBedId());
        patientList.remove(p);
        s.useIntDeleteInformation("patient","id", p.getId() );
    }

    public void updatePatientPrescription(Patient p , String prescription){
        for(Patient patient : patientList){
            if(patient.equals(p)){
                patient.setPrescriptions(prescription);
            }
        }
    }

    //getting and setting Methods
    public ArrayList<Staff> getStaffList() {
        return staffList;
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public Ward[] getWard() {
        return ward;
    }

    public ArrayList<Patient> getNoBedPatientsList() {
        return noBedPatientsList;
    }

}
