package model;


import java.util.HashMap;

public class Prescription {
    private int patientID;
    private int docID;
    private String reason;
    private HashMap<String,Medicine> medcineMap;

    public Prescription(int patientID, int docID, String reason ) {
        this.patientID = patientID;
        this.docID = docID;
        this.reason = reason;
        this.medcineMap = new HashMap<>();
    }


    public void addMedicine(String medName,Medicine m ){
        medcineMap.put(medName,m);
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDocID() {
        return docID;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public HashMap<String, Medicine> getMedcineMap() {
        return medcineMap;
    }

    public void setMedcineMap(HashMap<String, Medicine> medcineMap) {
        this.medcineMap = medcineMap;
    }
}
