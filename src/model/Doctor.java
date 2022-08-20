package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static controller.LoginController.STAFFID;

public class Doctor extends Staff {
    private HashMap<Integer,Prescription> prescriptionMap = new HashMap<>();
    private SqlConnect s = new SqlConnect();

    public Doctor(int id, String name, String password ,String position) {
        super(id, name, password, position);
    }

    public Doctor() {

    }

    //method read medicines and prescriptions from database
    public void initializePrescription(){
        try {
            ResultSet r = s.readDataBase("medicine_prescription");
            while (r.next()){
                String medName = r.getString("medName");
                int patientID = r.getInt("patientid");
                int time = r.getInt("time");
                int amount = r.getInt("amount");
                String unit = r.getString("unit");
                int docID = r.getInt("docid");
                String reason  = r.getString("reason");

                //Avoid duplicate prescriptions

                Medicine m = new Medicine(medName,time,amount, unit);
                if(prescriptionMap.containsKey(patientID)){
                    prescriptionMap.get(patientID).getMedcineMap().put(medName,m);
                } else{
                    Prescription p = new Prescription(patientID,docID,reason);
                    prescriptionMap.put(patientID,p);
                    p.getMedcineMap().put(medName,m);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Method check this doctor can attach a prescription or not
    public boolean allowAddPre(int pID, String hasPre)  {
        if( pID ==0 || hasPre.equals("yes")){
            return false;
        }
            return true;
    }

    //Method create a new prescription
    public Prescription addPrescription(int patientID , String reason){
        //create new prescription
        Prescription p = new Prescription(patientID,STAFFID,reason);
        return p;
    }

    //create a new new medicine
    public Medicine createMedicine(String mName, int mTime, int mAmount,String mUnit){
        Medicine m = new Medicine(mName,mTime,mAmount,mUnit);

        return m;
    }

    //method add a new medicine
    public void updateMedicine(AbleCareHome careHome,int pID, Prescription p, Medicine m)  {
        if(p.getMedcineMap().size() ==0 ) {
            careHome.getPatient(pID).setPrescriptions("has");
            s.updatePatientPrescription(p.getPatientID(), "has");
            String preDetails = "Doctor " + STAFFID+" Attach prescription for this patient At ";
            addDocLog(p.getPatientID(), preDetails);
        }

        //attach medicine to Prescription
        p.addMedicine(m.getMedName(),m);
        prescriptionMap.put(pID,p);

        //update to  to database
        s.insertMedicine(p, m);
        String medDetails = "Doctor " + STAFFID+"Attach medicine "+ m.getMedName()+ "for this patient prescription At ";
        addDocLog(p.getPatientID(),medDetails);
    }

    //connect to database update an log
    public void addDocLog(int patientID, String details){
        LogRecord logRecord = new  LogRecord (patientID, STAFFID, details);
        s.recordAnLog(logRecord);
    }

    //getPrescription  by patientID
    public Prescription getPrescription(int patientID){
        for(int pID: prescriptionMap.keySet()){
            if(patientID ==pID){
                return  prescriptionMap.get(pID) ;
            }
        }
        return null;
    }

    //Method remove a prescription
    public void removePrescription(Patient p, Prescription pre){
        //change patient &prescription information
        p.setPrescriptions("null");
        prescriptionMap.remove(p.getId());

        //update patient table in table
        s.updatePatientPrescription(p.getId(), "null");
        String details = "Doctor "+ STAFFID+ "Remove prescription (all medicines) from this patient At ";
        addDocLog(p.getId(), details);
        //delete all medicine from database
        for(Medicine m: pre.getMedcineMap().values()){
            s.deleteMedicine(m, pre.getPatientID());
        }
    }



    //Method check this doctor can add a new Medicine or not
    public boolean allowAddMedicine(Prescription pre, Medicine med){
        for(String mName: pre.getMedcineMap().keySet()){
            if(med.getMedName().equals(mName)){
                return false;
            }
        }
        return true;
    }

    //Method remove a medicine
    public void removeMedicine(Patient p,Prescription pre, Medicine m){
            //delete this medicine from prescription &database
            pre.getMedcineMap().remove(m.getMedName());
            s.deleteMedicine(m, pre.getPatientID());
            String details = "Doctor "+STAFFID + "Remove medicine "+ m.getMedName()+ " from this patient prescription At ";
            addDocLog(p.getId(), details);
    }



    //METHOD to check validation int(for medicine name )
    public boolean checkInt(String str) { // the check int method
        try {
            int theInt = Integer.parseInt(str); // check the enter can be transfer to int
            if (theInt > 0) {
                return true;
            } else {
                return false;
            } // use the true valid to close the do while loop
        } catch (Exception e) {
            return false; // if can not transfer to int, keep the valid still false
        }

    }


    //getting and setting
    public HashMap<Integer, Prescription> getPrescriptionMap() {
        return prescriptionMap;
    }

    public void setPrescriptionMap(HashMap<Integer, Prescription> prescriptionMap) {
        this.prescriptionMap = prescriptionMap;
    }

    public SqlConnect getS() {
        return s;
    }

    public void setS(SqlConnect s) {
        this.s = s;
    }
}



