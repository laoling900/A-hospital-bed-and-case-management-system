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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

public class NurseController implements Initializable {
    @FXML
    private Button logout;
    @FXML
    private Button exit;
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
    private Label patientInformation;
    @FXML
    private Label message;
    @FXML
    private Button selectPatient;
    @FXML
    private Label selectAnotherBed;
    @FXML
    private TableView prescriptionInfomation;
    @FXML
    private TableColumn<Medicine, String> medName = new TableColumn("Medicine");
    @FXML
    private TableColumn<Medicine, Number> time = new TableColumn("Time");
    @FXML
    private TableColumn<Medicine, Number> amount = new TableColumn("Amount");
    @FXML
    private TableColumn<Medicine, String> unit = new TableColumn("Unit");
    private AbleCareHome careHome = new AbleCareHome();
    private Doctor d = new Doctor();
    private Nurse n = new Nurse();
    private Patient pSave;
    private int bedSaveId = 0;
    private Patient tempP;
    private int tempBedID = 0;
    private Prescription pre;

    public NurseController() {
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

    //initialize the information GUI need
    public void initialize(URL u, ResourceBundle r) {
        this.careHome.initlizeCareHome();
        this.d.initializePrescription();
        this.showBedColor();
    }

    // show all bed color to the GUI
    private void showBedColor() {
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

    //show medicine details in a prescription when a patient has prescription
    private void showPrescriotion() {
        List<Medicine> medList = new ArrayList();
        Iterator var2 = this.pre.getMedcineMap().values().iterator();

        while(var2.hasNext()) {
            Medicine med = (Medicine)var2.next();
            medList.add(med);
        }

        this.prescriptionInfomation.setItems(FXCollections.observableList(medList));
        this.medName.setCellValueFactory(new PropertyValueFactory("medName"));
        this.time.setCellValueFactory(new PropertyValueFactory("time"));
        this.amount.setCellValueFactory(new PropertyValueFactory("amount"));
        this.unit.setCellValueFactory(new PropertyValueFactory("unit"));
        this.prescriptionInfomation.getColumns().removeAll(new Object[]{this.medName});
        this.prescriptionInfomation.getColumns().removeAll(new Object[]{this.time});
        this.prescriptionInfomation.getColumns().removeAll(new Object[]{this.amount});
        this.prescriptionInfomation.getColumns().removeAll(new Object[]{this.unit});
        this.prescriptionInfomation.getColumns().add(this.medName);
        this.prescriptionInfomation.getColumns().add(this.time);
        this.prescriptionInfomation.getColumns().add(this.amount);
        this.prescriptionInfomation.getColumns().add(this.unit);
    }

    // click any bed buttion on action
    public void clickBed(ActionEvent e) {
        // if already click select this patient button and save a patient information , then change bed
        if (this.bedSaveId != 0 && this.pSave != null) {
            int moveBedId = Integer.parseInt(((Button)e.getSource()).getId().substring(3));
            boolean giveBedSuccessful = this.careHome.movePatientToNewBed(this.pSave, moveBedId);
            if (giveBedSuccessful) {
                this.careHome.removePatientFromBed(this.pSave, this.pSave.getBedId());
                if (this.pSave.getGender().equals("FEMALE")) {
                    this.careHome.givePatientABed(this.pSave, moveBedId, BedEnum.FEMALE);
                    ((Button)e.getSource()).setStyle("-fx-background-color: red");
                } else if (this.pSave.getGender().equals("MALE")) {
                    this.careHome.givePatientABed(this.pSave, moveBedId, BedEnum.MALE);
                    ((Button)e.getSource()).setStyle("-fx-background-color: lightblue");
                }

                this.n.movePatientLog(this.pSave);
                this.message.setText("Change bed successful! ");
                this.selectAnotherBed.setText(" ");
                Iterator var4 = this.bedButtonMap().keySet().iterator();

                while(var4.hasNext()) {
                    int i = (Integer)var4.next();
                    if (this.bedSaveId == i) {
                        ((Button)this.bedButtonMap().get(i)).setStyle(" ");
                    }
                }
            } else {
                this.patientInformation.setText(" ");
                this.message.setText("Fail to change bed");
                this.selectAnotherBed.setText(" ");
            }

            this.pSave = null;
            this.tempP = null;
            this.bedSaveId = 0;
            this.tempBedID = 0;
            this.pre = null;
            this.selectPatient.setStyle(" ");
        //else not yet click select this patient button  , just check a patient information
        } else {
            //clear last time show patient information
            this.patientInformation.setText("");
            this.prescriptionInfomation.getItems().clear();
            this.tempP = null;
            this.pre = null;
            this.message.setText(" ");
            this.tempBedID = Integer.parseInt(((Button)e.getSource()).getId().substring(3));

            //get new patient information
            try {
                this.tempP = this.careHome.getPatientbyBedID(this.tempBedID);
            } catch (NoPatientException var6) {
            }

            if (this.tempP != null) {
                Label var10000 = this.patientInformation;
                int var10001 = this.tempP.getId();
                var10000.setText("ID: " + var10001 + "    Name: " + this.tempP.getName() + " Gender: " + this.tempP.getGender() + "\nNeedIsolation: " + this.tempP.getNeedIsolation());
                if (!this.tempP.getPrescriptions().equals("null")) {
                    this.pre = (Prescription)this.d.getPrescriptionMap().get(this.tempP.getId());
                    this.showPrescriotion();
                }
            }
        }

    }

    // if select a bed and this bed has patient, then user click select this patient button, patient information will be save
    public void selectThisPatient(ActionEvent e) {
        if (this.tempP != null) {
            this.bedSaveId = this.tempBedID;
            this.pSave = this.tempP;
            this.selectAnotherBed.setText("Pls select a new EMPTY bed ==>>");
            this.selectPatient.setStyle("-fx-background-color: red");
        } else {
            this.message.setText("Pls select a bed with Patient first ! ");
        }

    }

    // if a patient has prescription , user can click take Medicine button  and save an log
    public void takeMedicineAction(ActionEvent e) {
        if (this.tempP != null && this.pre != null) {
            this.n.takeMedicine(this.pre, this.tempP);
            this.message.setText("take medicine successful !");
        } else {
            this.message.setText("Pls select a patient with prescription first !");
        }

    }

    //login out system , go to the login page
    public void loginOutAction(ActionEvent e) throws IOException {
        Stage stage = (Stage)this.logout.getScene().getWindow();
        FXMLLoader l = new FXMLLoader();
        l.setLocation(this.getClass().getResource("/View/Login.fxml"));
        stage.setTitle("Login page");
        Scene scene = new Scene((Parent)l.load());
        stage.setScene(scene);
        stage.show();
    }

    //exit System button , save all log
    public void exitSystemButtonAction(ActionEvent event) {
        Stage stage = (Stage)this.exit.getScene().getWindow();
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
