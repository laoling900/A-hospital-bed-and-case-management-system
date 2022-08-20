package model;

public class Patient {
    private int id;
    private String name;
    private String gender;   //true for female , false for male
    private String needIsolation;  //medical conditional
    private String prescriptions;
    private int bedId;

    public Patient(int id, String name, String gender, String needIsolation, String prescriptions,int bedId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.needIsolation = needIsolation;
        this.prescriptions = prescriptions;
        this.bedId = bedId;
    }

    //All getting and setting methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public String getNeedIsolation() {
        return needIsolation;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }
}
