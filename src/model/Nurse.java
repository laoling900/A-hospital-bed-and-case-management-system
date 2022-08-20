package model;


import static controller.LoginController.STAFFID;

public class Nurse extends Staff {
    private SqlConnect s = new SqlConnect();

    public Nurse(int id,String name,   String password, String position) {
        super( id, name, password,  position);
    }

    public Nurse() {

    }

    //Method to take Medicine ,which will save an log to the syetem
    public void takeMedicine(Prescription pre, Patient p ){
        for(Medicine m :pre.getMedcineMap().values()){
            String details = "Nurse "+STAFFID+" give medicine to Patient "+p.getId()+" for "+ m.toString()+" At ";
            LogRecord l = new LogRecord(p.getId(), STAFFID, details);
            s.recordAnLog(l);
        }
    }

    //Method record a log to the database
    public void movePatientLog(Patient p){
        String details = "Nurse "+STAFFID +" move this patient to new Bed "+p.getBedId();
        LogRecord log = new LogRecord(p.getId(), STAFFID, details);
        s.recordAnLog(log);
    }

}
