package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AbleCareHome;
import model.BedEnum;
import model.Doctor;
import model.DoctorShift;
import model.LogRecord;
import model.Manager;
import model.Nurse;
import model.NurseShift;
import model.Patient;
import model.SpecialCharactersException;
import model.Staff;

public class ManagerController implements Initializable {
    private AbleCareHome careHome = new AbleCareHome();
    private Manager m = new Manager();
    private LogRecord r = new LogRecord();
    @FXML
    private TextField patientNameTextField;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton yes;
    @FXML
    private RadioButton no;
    @FXML
    private Label addPatientMessageLabel;
    @FXML
    private Label patientInformation;
    @FXML
    private Button bed10101;
    @FXML
    private Button bed10201;
    @FXML
    private Button bed10202;
    @FXML
    private Button bed10301;
    @FXML
    private Button bed10302;
    @FXML
    private Button bed10303;
    @FXML
    private Button bed10304;
    @FXML
    private Button bed10401;
    @FXML
    private Button bed10402;
    @FXML
    private Button bed10403;
    @FXML
    private Button bed10404;
    @FXML
    private Button bed10501;
    @FXML
    private Button bed10502;
    @FXML
    private Button bed10503;
    @FXML
    private Button bed10504;
    @FXML
    private Button bed10601;
    @FXML
    private Button bed10602;
    @FXML
    private Button bed10603;
    @FXML
    private Button bed10604;
    @FXML
    private Button bed20101;
    @FXML
    private Button bed20201;
    @FXML
    private Button bed20202;
    @FXML
    private Button bed20301;
    @FXML
    private Button bed20302;
    @FXML
    private Button bed20303;
    @FXML
    private Button bed20304;
    @FXML
    private Button bed20401;
    @FXML
    private Button bed20402;
    @FXML
    private Button bed20403;
    @FXML
    private Button bed20404;
    @FXML
    private Button bed20501;
    @FXML
    private Button bed20502;
    @FXML
    private Button bed20503;
    @FXML
    private Button bed20504;
    @FXML
    private Button bed20601;
    @FXML
    private Button bed20602;
    @FXML
    private Button bed20603;
    @FXML
    private Button bed20604;
    @FXML
    private ChoiceBox<String> patientWithoutBed;
    @FXML
    private Label showAddBedInformation;
    @FXML
    private RadioButton doctor;
    @FXML
    private RadioButton nurse;
    @FXML
    private TextField newStaffName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label addStaffMessage;
    @FXML
    private Label showNewStaffInformation;
    @FXML
    private TableView<Staff> staffTable;
    @FXML
    TableColumn<Staff, Number> id = new TableColumn("id");
    @FXML
    TableColumn<Staff, String> name = new TableColumn("name");
    @FXML
    TableColumn<Staff, String> password = new TableColumn("password");
    @FXML
    TableColumn<Staff, String> position = new TableColumn("position");
    @FXML
    private TextField newName;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Label modifyInformation;
    @FXML
    private TableView<Doctor> doctorTable;
    @FXML
    private TableColumn<Doctor, Number> dID = new TableColumn("id");
    @FXML
    private TableColumn<Doctor, String> dName = new TableColumn("name");
    @FXML
    private TableView<DoctorShift> docShiftTable;
    @FXML
    private TableColumn<DoctorShift, Number> docShiftID = new TableColumn("Shift id");
    @FXML
    private TableColumn<DoctorShift, Number> docID = new TableColumn("Doctor id");
    @FXML
    private TableColumn<DoctorShift, String> docWeek = new TableColumn("Week");
    @FXML
    private TableColumn<DoctorShift, String> docTime = new TableColumn("Time");
    @FXML
    private ChoiceBox<String> docDayChoice;
    @FXML
    private ChoiceBox<String> docTimeChoice;
    @FXML
    private Spinner docTimesSpinner;
    @FXML
    private Label docShiftChangeInfo;
    @FXML
    private TableView<Nurse> nurseTable;
    @FXML
    private TableColumn<Nurse, Number> nID = new TableColumn("id");
    @FXML
    private TableColumn<Nurse, String> nName = new TableColumn("name");
    @FXML
    private TableView<NurseShift> nurShiftTable;
    @FXML
    private TableColumn<NurseShift, Number> nurShiftID = new TableColumn("Shift id");
    @FXML
    private TableColumn<NurseShift, Number> nurseID = new TableColumn("Nurse id");
    @FXML
    private TableColumn<NurseShift, String> nurWeek = new TableColumn("Week");
    @FXML
    private TableColumn<NurseShift, String> nurTime = new TableColumn("Time");
    @FXML
    private ChoiceBox<String> nurDayChoice;
    @FXML
    private RadioButton nurMorningShift;
    @FXML
    private RadioButton nurAfternoonShift;
    @FXML
    private Label nurShiftChangeInfo;
    @FXML
    private Button logOut;
    @FXML
    private Button exitSystemButton;
    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, Number> pID = new TableColumn("id");
    @FXML
    private TableColumn<Patient, String> pName = new TableColumn("name");
    @FXML
    private TableColumn<Patient, Number> bedID = new TableColumn("bedId");
    @FXML
    private Label dischargeInfo;

    public ManagerController() {
    }

    public HashMap<Integer, Button> bedButtonMap() {
        HashMap<Integer, Button> bedButtonMap = new HashMap();
        bedButtonMap.put(10101, this.bed10101);
        bedButtonMap.put(10201, this.bed10201);
        bedButtonMap.put(10202, this.bed10202);
        bedButtonMap.put(10301, this.bed10301);
        bedButtonMap.put(10302, this.bed10302);
        bedButtonMap.put(10303, this.bed10303);
        bedButtonMap.put(10304, this.bed10304);
        bedButtonMap.put(10401, this.bed10401);
        bedButtonMap.put(10402, this.bed10402);
        bedButtonMap.put(10403, this.bed10403);
        bedButtonMap.put(10404, this.bed10404);
        bedButtonMap.put(10501, this.bed10501);
        bedButtonMap.put(10502, this.bed10502);
        bedButtonMap.put(10503, this.bed10503);
        bedButtonMap.put(10504, this.bed10504);
        bedButtonMap.put(10601, this.bed10601);
        bedButtonMap.put(10602, this.bed10602);
        bedButtonMap.put(10603, this.bed10603);
        bedButtonMap.put(10604, this.bed10604);
        bedButtonMap.put(20101, this.bed20101);
        bedButtonMap.put(20201, this.bed20201);
        bedButtonMap.put(20202, this.bed20202);
        bedButtonMap.put(20301, this.bed20301);
        bedButtonMap.put(20302, this.bed20302);
        bedButtonMap.put(20303, this.bed20303);
        bedButtonMap.put(20304, this.bed20304);
        bedButtonMap.put(20401, this.bed20401);
        bedButtonMap.put(20402, this.bed20402);
        bedButtonMap.put(20403, this.bed20403);
        bedButtonMap.put(20404, this.bed20404);
        bedButtonMap.put(20501, this.bed20501);
        bedButtonMap.put(20502, this.bed20502);
        bedButtonMap.put(20503, this.bed20503);
        bedButtonMap.put(20504, this.bed20504);
        bedButtonMap.put(20601, this.bed20601);
        bedButtonMap.put(20602, this.bed20602);
        bedButtonMap.put(20603, this.bed20603);
        bedButtonMap.put(20604, this.bed20604);
        return bedButtonMap;
    }

    // initialize all the information in the memory and the GUI
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.careHome.initlizeCareHome();
        this.m.initlizeShifts();
        this.showPatientWithoutBed();
        this.showBedColor();
        this.showStaffList();
        this.showDoctors();
        this.showDoctorShiftTable();
        this.showNurses();
        this.showNurShiftTable();
        this.initlizDate();
        this.doctorTime();
        this.showPatientTable();
    }

    // initialize the patient who without bed
    public void showPatientWithoutBed() {
        this.patientWithoutBed.getItems().clear();

        for(int i = 0; i < this.careHome.getNoBedPatientsList().size(); ++i) {
            Patient p = (Patient)this.careHome.getNoBedPatientsList().get(i);
            int var10000 = p.getId();
            String patientInformation = var10000 + " " + p.getName() + " " + p.getGender();
            this.patientWithoutBed.getItems().add(patientInformation);
        }

    }

    // initialize the doctor shift table
    public void showDoctorShiftTable() {
        List<DoctorShift> docShiftsList = this.m.getDoctorShifts();
        this.docShiftTable.setItems(FXCollections.observableList(docShiftsList));
        this.docShiftID.setCellValueFactory(new PropertyValueFactory("shiftId"));
        this.docID.setCellValueFactory(new PropertyValueFactory("doctorId"));
        this.docWeek.setCellValueFactory(new PropertyValueFactory("week"));
        this.docTime.setCellValueFactory(new PropertyValueFactory("time"));
        this.docShiftTable.getColumns().removeAll(new TableColumn[]{this.docShiftID});
        this.docShiftTable.getColumns().removeAll(new TableColumn[]{this.docID});
        this.docShiftTable.getColumns().removeAll(new TableColumn[]{this.docWeek});
        this.docShiftTable.getColumns().removeAll(new TableColumn[]{this.docTime});
        this.docShiftTable.getColumns().add(this.docShiftID);
        this.docShiftTable.getColumns().add(this.docID);
        this.docShiftTable.getColumns().add(this.docWeek);
        this.docShiftTable.getColumns().add(this.docTime);
    }

    // initialize the nurse shift
    public void showNurShiftTable() {
        List<NurseShift> nurseShiftList = this.m.getNurseShifts();
        this.nurShiftTable.setItems(FXCollections.observableList(nurseShiftList));
        this.nurShiftID.setCellValueFactory(new PropertyValueFactory("shiftId"));
        this.nurseID.setCellValueFactory(new PropertyValueFactory("nurseId"));
        this.nurWeek.setCellValueFactory(new PropertyValueFactory("week"));
        this.nurTime.setCellValueFactory(new PropertyValueFactory("time"));
        this.nurShiftTable.getColumns().removeAll(new TableColumn[]{this.nurShiftID});
        this.nurShiftTable.getColumns().removeAll(new TableColumn[]{this.nurseID});
        this.nurShiftTable.getColumns().removeAll(new TableColumn[]{this.nurWeek});
        this.nurShiftTable.getColumns().removeAll(new TableColumn[]{this.nurTime});
        this.nurShiftTable.getColumns().add(this.nurShiftID);
        this.nurShiftTable.getColumns().add(this.nurseID);
        this.nurShiftTable.getColumns().add(this.nurWeek);
        this.nurShiftTable.getColumns().add(this.nurTime);
    }



    public void showPatientTable(){
        List<Patient> patientList = careHome.getPatientList();
        patientTable.setItems(FXCollections.observableList(patientList));
        pID.setCellValueFactory(new PropertyValueFactory<>("id"));
        pName.setCellValueFactory(new PropertyValueFactory<>("name"));
        bedID.setCellValueFactory(new PropertyValueFactory<>("bedId"));
        patientTable.getColumns().removeAll(pID);
        patientTable.getColumns().removeAll(pName);
        patientTable.getColumns().removeAll(bedID);
        patientTable.getColumns().add(pID);
        patientTable.getColumns().add(pName);
        patientTable.getColumns().add(bedID);
    }

    public void dischargePatient(ActionEvent e){
        if(patientTable.getSelectionModel().isEmpty()){
            dischargeInfo.setText("Pls select a patient first ! ");
        }else{
            Patient p = patientTable.getSelectionModel().getSelectedItem();
            try {
                r.saveAPatientLog(p.getId());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            careHome.removePatient(p);
            showPatientTable();
        showBedColor();
            dischargeInfo.setText("Dischareg successful ! , a log file for this patient has been create ! ");
        }
    }


    //add patient button on action
    public void addPatientButtonAction(ActionEvent event) {
        if (this.patientNameTextField.getText().isBlank() || !this.male.isSelected() && !this.female.isSelected() || !this.yes.isSelected() && !this.no.isSelected()) {
            this.addPatientMessageLabel.setText("Pls finish all enter and choice");
        } else {
            try {
                this.validatePatient();
            } catch (SpecialCharactersException var3) {
                var3.printStackTrace();
                this.addPatientMessageLabel.setText("Special characters like %#! not allow!");
            }
        }

    }

    // check the patient information from GUI validation or not
    public void validatePatient() throws SpecialCharactersException {
        // go to the manager method to check the name validation
        if (!this.m.checkString(this.patientNameTextField.getText())) {
            this.addPatientMessageLabel.setText("Special characters like %#! not allow!");
        } else {
            String name = this.patientNameTextField.getText();
            String gender = null;
            if (this.male.isSelected()) {
                gender = "MALE";
            } else if (this.female.isSelected()) {
                gender = "FEMALE";
            }

            String needIsolation = null;
            if (this.yes.isSelected()) {
                needIsolation = "yes";
            } else if (this.no.isSelected()) {
                needIsolation = "no";
            }
            // go to the doctor class to create a new doctor
            Patient newPatient = this.m.addPatient(this.careHome, name, gender, needIsolation);
            //save the new patient to care home class
            this.careHome.addNewPatient(newPatient);
            this.m.addNewPatientLog(newPatient);
            this.addPatientMessageLabel.setText("New Patient has been added ! ");
            this.patientInformation.setText("ID: " + newPatient.getId() + "\nName: " + name + "\ngender: " + gender + "\nneedIsolation: " + needIsolation);
            this.showPatientWithoutBed();
        }

    }

    // initlize the day choice box for doctor and nurse shift tab
    public void initlizDate() {
        String[] d = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] var2 = d;
        int var3 = d.length;

        int var4;
        String day;
        for(var4 = 0; var4 < var3; ++var4) {
            day = var2[var4];
            this.docDayChoice.getItems().add(day);
        }

        var2 = d;
        var3 = d.length;

        for(var4 = 0; var4 < var3; ++var4) {
            day = var2[var4];
            this.nurDayChoice.getItems().add(day);
        }

    }

    // initlize the doctor time choice box and spinner
    public void doctorTime() {
        for(int i = 0; i < 24; ++i) {
            this.docTimeChoice.getItems().add( String.valueOf(i));
        }

        ObservableList<String> min = FXCollections.observableArrayList();
        min.add("00");
        min.add("30");
        SpinnerValueFactory<String> valueFactory = new ListSpinnerValueFactory(min);
        this.docTimesSpinner.setValueFactory(valueFactory);
    }

    // show Bed Coolor to the GUI so that manager know some bed already been taken
    public void showBedColor() {
        HashMap<Integer, Button> buttonMap = this.bedButtonMap();
        Iterator var2 = buttonMap.keySet().iterator();

        while(var2.hasNext()) {
            int bID = (Integer)var2.next();
            if (bID == this.careHome.getBed(bID).getbID()) {
                if (this.careHome.getBed(bID).getBedEnum().equals(BedEnum.MALE)) {
                    ((Button)buttonMap.get(bID)).setStyle("-fx-background-color: lightblue");
                } else if (this.careHome.getBed(bID).getBedEnum().equals(BedEnum.FEMALE)) {
                    ((Button)buttonMap.get(bID)).setStyle("-fx-background-color: red");
                }
            }
        }
    }

    // if a manager click a bed , he wants to add patient to a new bed
    public void clickBed(ActionEvent e) {
        if (this.patientWithoutBed.getSelectionModel().isEmpty()) {
            this.showAddBedInformation.setText("You should select a patient first ! ");
        } else {
            String pID = ((String)this.patientWithoutBed.getValue()).substring(0, 4);
            Patient p = this.careHome.getPatient(Integer.parseInt(pID));
            int bedId = Integer.parseInt(((Button)e.getSource()).getId().substring(3));
            boolean isEmptyBed = this.m.isEmptyBed(this.careHome, bedId);
            boolean genderMatch = this.careHome.checkRoomGender(bedId, p.getGender());
            if (isEmptyBed && genderMatch) {
                if (p.getGender().equals("FEMALE")) {
                    this.careHome.givePatientABed(p, bedId, BedEnum.FEMALE);
                    ((Button)e.getSource()).setStyle("-fx-background-color: red");
                } else if (p.getGender().equals("MALE")) {
                    this.careHome.givePatientABed(p, bedId, BedEnum.MALE);
                    ((Button)e.getSource()).setStyle("-fx-background-color: lightblue");
                }

                this.showAddBedInformation.setText("Add to bed successful! ");
                this.showPatientWithoutBed();
                this.m.giveNewPatientBedLog(p);
            } else {
                this.showAddBedInformation.setText("Fail to give bed  !");
            }

        }
    }

    public void addStaffAction(ActionEvent event) {
        if (!this.newStaffName.getText().isBlank() && !this.passwordField.getText().isBlank()) {
            try {
                this.validateStaff();
            } catch (SpecialCharactersException var3) {
                var3.printStackTrace();
            }
        } else {
            this.addStaffMessage.setText("Pls fill all information");
        }

    }

    public void validateStaff() throws SpecialCharactersException {
        if (!this.m.checkString(this.newStaffName.getText())) {
            this.addStaffMessage.setText("Special characters like %#! not allow!");
        } else {
            String name = this.newStaffName.getText();
            String position = null;
            if (this.doctor.isSelected()) {
                position = "DOCTOR";
            } else if (this.nurse.isSelected()) {
                position = "NURSE";
            }

            String password = this.passwordField.getText();
            int nextID;
            if (this.careHome.getStaffList().isEmpty()) {
                nextID = 1001;
            } else {
                nextID = ((Staff)this.careHome.getStaffList().get(this.careHome.getStaffList().size() - 1)).getId() + 1;
            }

            Staff s = null;
            if (position.equals("DOCTOR")) {
                s = new Doctor(nextID, name, password, "DOCTOR");
                this.m.addStaff(this.careHome, s);
                this.showNewStaffInformation.setText("ID: " + nextID + "\nName: " + name + "\npassword: " + password + "\nPosition: DOCTOR");
            } else if (position.equals("NURSE")) {
                s = new Nurse(nextID, name, password, "NURSE");
                this.m.addStaff(this.careHome, s);
                this.showNewStaffInformation.setText("ID: " + nextID + "\nName: " + name + "\npassword: " + password + "\nPosition: NURSE");
            }

            this.showStaffList();
            this.addStaffMessage.setText("New Staff has been added! ");
        }

    }

    public void showStaffList() {
        List<Staff> staff = this.careHome.getStaffList();
        this.staffTable.setItems(FXCollections.observableList(staff));
        this.id.setCellValueFactory(new PropertyValueFactory("id"));
        this.name.setCellValueFactory(new PropertyValueFactory("name"));
        this.password.setCellValueFactory(new PropertyValueFactory("password"));
        this.position.setCellValueFactory(new PropertyValueFactory("position"));
        this.staffTable.getColumns().removeAll(new TableColumn[]{this.id});
        this.staffTable.getColumns().removeAll(new TableColumn[]{this.name});
        this.staffTable.getColumns().removeAll(new TableColumn[]{this.password});
        this.staffTable.getColumns().removeAll(new TableColumn[]{this.position});
        this.staffTable.getColumns().add(this.id);
        this.staffTable.getColumns().add(this.name);
        this.staffTable.getColumns().add(this.password);
        this.staffTable.getColumns().add(this.position);
    }

    public void showDoctors() {
        List<Doctor> doctorList = new ArrayList();
        Iterator var2 = this.careHome.getStaffList().iterator();

        while(var2.hasNext()) {
            Staff s = (Staff)var2.next();
            if (s instanceof Doctor) {
                Doctor doc = (Doctor)s;
                doctorList.add(doc);
            }
        }

        this.doctorTable.setItems(FXCollections.observableList(doctorList));
        this.dID.setCellValueFactory(new PropertyValueFactory("id"));
        this.dName.setCellValueFactory(new PropertyValueFactory("name"));
        this.doctorTable.getColumns().removeAll(new TableColumn[]{this.dID});
        this.doctorTable.getColumns().removeAll(new TableColumn[]{this.dName});
        this.doctorTable.getColumns().add(this.dID);
        this.doctorTable.getColumns().add(this.dName);
    }

    public void showNurses() {
        List<Nurse> nurseList = new ArrayList();
        Iterator var2 = this.careHome.getStaffList().iterator();

        while(var2.hasNext()) {
            Staff s = (Staff)var2.next();
            if (s instanceof Nurse) {
                Nurse n = (Nurse)s;
                nurseList.add(n);
            }
        }

        this.nurseTable.setItems(FXCollections.observableList(nurseList));
        this.nID.setCellValueFactory(new PropertyValueFactory("id"));
        this.nName.setCellValueFactory(new PropertyValueFactory("name"));
        this.nurseTable.getColumns().removeAll(new TableColumn[]{this.nID});
        this.nurseTable.getColumns().removeAll(new TableColumn[]{this.nName});
        this.nurseTable.getColumns().add(this.nID);
        this.nurseTable.getColumns().add(this.nName);
    }

    public void modifyStaffAction(ActionEvent e) {
        if (!this.newName.getText().isBlank() && !this.newPassword.getText().isBlank()) {
            Staff s = (Staff)this.staffTable.getSelectionModel().getSelectedItem();
            String nName = this.newName.getText();
            String nPassword = this.newPassword.getText();
            this.m.modifyStaff(s, nName, nPassword);
            this.modifyInformation.setText("Modify successful! ");
            this.staffTable.refresh();
            this.showDoctors();
            this.showNurses();
        } else {
            this.modifyInformation.setText("Pls fill all ");
        }

    }

    public void createNurShift(ActionEvent e) {
        if (!this.nurseTable.getSelectionModel().isEmpty() && !this.nurDayChoice.getSelectionModel().isEmpty()) {
            Nurse n = (Nurse)this.nurseTable.getSelectionModel().getSelectedItem();
            String day = (String)this.nurDayChoice.getValue();
            String time = null;
            if (this.nurMorningShift.isSelected()) {
                time = "8am-4pm";
            } else {
                if (!this.nurAfternoonShift.isSelected()) {
                    this.nurShiftChangeInfo.setText("Pls fill all information 3! ");
                    return;
                }

                time = "2pm-10pm";
            }

            boolean hasSameDayShift = this.m.checkNurSameDayShift(n, day);
            if (!hasSameDayShift && day != null && time != null) {
                int nextID = 0;
                Iterator var7 = this.m.getNurseShifts().iterator();

                while(var7.hasNext()) {
                    NurseShift ns = (NurseShift)var7.next();
                    if (ns.getShiftId() > nextID) {
                        nextID = ns.getShiftId();
                    }
                }

                ++nextID;
                NurseShift newShift = new NurseShift(nextID, n.getId(), day, time);
                this.m.updateNurShift(newShift);
                this.showNurShiftTable();
                this.nurShiftChangeInfo.setText("Successful to create a new shift !");
            } else {
                this.nurShiftChangeInfo.setText("fail to creat, this nurse already have same day shift !");
            }

        } else {
            this.nurShiftChangeInfo.setText("Pls fill all information !");
        }
    }

    public void createDocShift(ActionEvent e) {
        if (!this.doctorTable.getSelectionModel().isEmpty() && !this.docDayChoice.getSelectionModel().isEmpty() && !this.docTimeChoice.getSelectionModel().isEmpty()) {
            Doctor doc = (Doctor)this.doctorTable.getSelectionModel().getSelectedItem();
            String day = (String)this.docDayChoice.getValue();
            String time = (String)this.docTimeChoice.getValue();
            String min = this.docTimesSpinner.getValue().toString();
            String jointTime = time + ":" + min;
            boolean hasSameDayShift = this.m.checkDocSameDayShift(doc, day);
            if (!hasSameDayShift) {
                int nextid = 0;
                Iterator var7 = this.m.getDoctorShifts().iterator();

                while(var7.hasNext()) {
                    DoctorShift ds = (DoctorShift)var7.next();
                    if (ds.getShiftId() > nextid) {
                        nextid = ds.getShiftId();
                    }
                }

                ++nextid;
                DoctorShift newShift = new DoctorShift(nextid, doc.getId(), day, jointTime);
                this.m.updateDoctorShift(newShift);
                this.showDoctorShiftTable();
                this.docShiftChangeInfo.setText("Successful to create !");
            } else {
                this.docShiftChangeInfo.setText("fail to create, this doctor already have same day shift");
            }

        } else {
            this.docShiftChangeInfo.setText("Pls fill all information ! ");
        }
    }

    public void removeDoctorShift(ActionEvent e)  {
        DoctorShift ds = (DoctorShift)this.docShiftTable.getSelectionModel().getSelectedItem();
        this.m.removeDoctorShift(ds);
        this.docShiftTable.refresh();
        this.docShiftChangeInfo.setText("The shift has been delete !");
    }

    public void removeNurShift(ActionEvent e) {
        NurseShift ns = (NurseShift)this.nurShiftTable.getSelectionModel().getSelectedItem();
        this.m.removeNurShift(ns);
        this.nurShiftTable.refresh();
        this.nurShiftChangeInfo.setText("The shift has been delete !");
    }

    public void loginOutAction(ActionEvent e) throws IOException {
        Stage stage = (Stage)this.logOut.getScene().getWindow();
        FXMLLoader l = new FXMLLoader();
        l.setLocation(this.getClass().getResource("/View/Login.fxml"));
        stage.setTitle("Login page");
        Scene scene = new Scene((Parent)l.load());
        stage.setScene(scene);
        stage.show();
    }

    public void exitSystemButtonAction(ActionEvent event) {
        Stage stage = (Stage)this.exitSystemButton.getScene().getWindow();
        LogRecord l = new LogRecord();

        try {
            l.saveAllLogToFile();
        } catch (IOException var5) {
            var5.printStackTrace();
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        stage.close();
    }
}
