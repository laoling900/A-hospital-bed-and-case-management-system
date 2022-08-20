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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

public class DoctorController implements Initializable {
    private AbleCareHome careHome = new AbleCareHome();
    private Doctor d = new Doctor();
    private int bedID;
    private Patient p;
    private Prescription prescription = null;
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
    private Label prescriptionInfo;
    @FXML
    private Label message;
    @FXML
    private TextArea reason;
    @FXML
    private Label docInformation;
    @FXML
    private TableView<Medicine> medicineTable;
    @FXML
    private TableColumn<Medicine, String> medName = new TableColumn("Medicine");
    @FXML
    private TableColumn<Medicine, Number> time = new TableColumn("Time");
    @FXML
    private TableColumn<Medicine, Number> amount = new TableColumn("Amount");
    @FXML
    private TableColumn<Medicine, String> unit = new TableColumn("Unit");
    @FXML
    private TextField medicineName;
    @FXML
    private TextField medicineTime;
    @FXML
    private TextField medicineAmount;
    @FXML
    private TextField medicineUnit;
    @FXML
    private Label medicineMessage;

    public DoctorController() {
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

    // initialize information that GUI need
    public void initialize(URL u, ResourceBundle r) {
        this.docInformation.setText("Welcome, Doctor " + LoginController.STAFFID);
        this.careHome.initlizeCareHome();
        this.d.initializePrescription();
        this.showBedColor();
    }

    //show bed color to the GUI
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

    //show medicine information to the GUI
    public void showMedTable(Prescription prescription) {
        List<Medicine> medList = new ArrayList();
        Iterator var3 = prescription.getMedcineMap().values().iterator();

        while(var3.hasNext()) {
            Medicine med = (Medicine)var3.next();
            medList.add(med);
        }

        this.medicineTable.setItems(FXCollections.observableList(medList));
        this.medName.setCellValueFactory(new PropertyValueFactory("medName"));
        this.time.setCellValueFactory(new PropertyValueFactory("time"));
        this.amount.setCellValueFactory(new PropertyValueFactory("amount"));
        this.unit.setCellValueFactory(new PropertyValueFactory("unit"));
        this.medicineTable.getColumns().removeAll(new TableColumn[]{this.medName});
        this.medicineTable.getColumns().removeAll(new TableColumn[]{this.time});
        this.medicineTable.getColumns().removeAll(new TableColumn[]{this.amount});
        this.medicineTable.getColumns().removeAll(new TableColumn[]{this.unit});
        this.medicineTable.getColumns().add(this.medName);
        this.medicineTable.getColumns().add(this.time);
        this.medicineTable.getColumns().add(this.amount);
        this.medicineTable.getColumns().add(this.unit);
    }

    //user click a bed action
    public void clickBed(ActionEvent e) {
        this.bedID = Integer.parseInt(((Button)e.getSource()).getId().substring(3));
        //go carehome model to get the patient if has any
        try {
            this.p = this.careHome.getPatientbyBedID(this.bedID);
        //if no patient , catch exception and clear all information
        } catch (NoPatientException var3) {
            this.patientInformation.setText("This bed has no patient");
            this.prescriptionInfo.setText(" ");
            this.prescription = null;
            this.p = null;
            this.medicineTable.getItems().clear();
            return;
        }

        Label var10000 = this.patientInformation;
        int var10001 = this.p.getId();
        var10000.setText("ID:  " + var10001 + "   Name:  " + this.p.getName() + "  Gender:  " + this.p.getGender() + "   \nNeedIsolation:  " + this.p.getNeedIsolation() + "   Prescription: " + this.p.getPrescriptions());
        // check this bed 's patient has prescription or not
        if (this.p.getPrescriptions().equals("null")) {
            this.prescriptionInfo.setText("No prescription for this patient, \n You can create a new one ! ");
            this.medicineTable.getItems().clear();
        // if this patient has prescription, show all medicine to the medicine table view
        } else {
            this.prescription = this.d.getPrescription(this.p.getId());
            int docID = this.prescription.getDocID();
            if (docID == LoginController.STAFFID) {
                this.prescriptionInfo.setText("He has prescription given by you ,\nYou can delete here, \nOr change details at second page ");
                this.showMedTable(this.prescription);
            } else {
                this.prescriptionInfo.setText("He has prescription given by: " + this.prescription.getDocID() + "\nYou can't change it ! ");
                this.medicineTable.getItems().clear();
            }
        }

    }

    // click the addPre button on action
    public void addPre(ActionEvent e) {
        if (this.reason.getText().isBlank()) {
            this.message.setText("Pls fill reason ! ");
        } else {
            this.message.setText("");
            boolean allowAddPre = this.d.allowAddPre(this.p.getId(), this.p.getPrescriptions());
            if (allowAddPre) {
                this.prescription = this.d.addPrescription(this.p.getId(), this.reason.getText());
                this.message.setText("You can attach prescription now ! ");
                this.prescriptionInfo.setText("Pls go to the second page, \nat least 1 medicine should be attach ");
                this.reason.clear();
            } else {
                this.message.setText("This patient already had a prescription");
            }

        }
    }

    //click the addMedicine buttion on action
    public void addMedicine(ActionEvent e) {
        if (!this.medicineName.getText().isBlank() && !this.medicineTime.getText().isBlank() && !this.medicineAmount.getText().isBlank() && !this.medicineUnit.getText().isBlank()) {
            this.validationMed();
        } else {
            this.medicineMessage.setText("Pls fill all details ");
        }

    }

    // check this medicine is value or not
    public void validationMed() {
        if (this.d.checkInt(this.medicineTime.getText()) && this.d.checkInt(this.medicineAmount.getText())) {
            String mName = this.medicineName.getText();
            int mTime = Integer.parseInt(this.medicineTime.getText());
            int mAmount = Integer.parseInt(this.medicineAmount.getText());
            String mUnit = this.medicineUnit.getText();
            //if information collect from GUI value, as doctor create a medicine
            Medicine m = this.d.createMedicine(mName, mTime, mAmount, mUnit);
            // go to doctor method check has same medicine in prescription or not
            boolean allowAddMedicine = this.d.allowAddMedicine(this.prescription, m);
            if (allowAddMedicine) {
                this.d.updateMedicine(this.careHome, this.p.getId(), this.prescription, m);
                this.prescription.addMedicine(mName, m);
                this.medicineMessage.setText("Successful give medicine ! ");
                this.showMedTable(this.prescription);
                this.medicineTable.refresh();
                this.medicineName.clear();
                this.medicineTime.clear();
                this.medicineAmount.clear();
                this.medicineUnit.clear();
            } else {
                this.medicineMessage.setText("Fail to add, already has this medicine ! ");
            }
        } else {
            this.medicineMessage.setText("Time and Amount must put Integer ! ");
        }

    }

    // click the delete prescription button on action
    public void deletePre(ActionEvent e) {
        if (this.prescription == null) {
            this.message.setText("Nothing can be delete !");
        } else if (this.prescription.getDocID() != LoginController.STAFFID) {
            this.message.setText("He has prescription given by: " + this.prescription.getDocID() + "You can't change it ! ");
        } else {
            this.d.removePrescription(this.p, this.prescription);
            this.prescriptionInfo.setText("He didn't has any prescription, \nyou can create a new one ");
            this.message.setText("Remove successful ! ");
            this.prescription = null;
            this.medicineTable.refresh();
        }

    }

    //click delete medicine buttion on action
    public void deleteMed(ActionEvent e) {
        if (this.medicineTable.getSelectionModel().isEmpty()) {
            this.medicineMessage.setText("Pls select a medicine first !");
        } else if (this.prescription.getMedcineMap().size() > 1) {
            Medicine m = (Medicine)this.medicineTable.getSelectionModel().getSelectedItem();
            this.d.removeMedicine(this.p, this.prescription, m);
            this.showMedTable(this.prescription);
            this.medicineMessage.setText("Delete a medicine successful !");
        } else {
            this.medicineMessage.setText("At least 1 medicine must in the prescription ! ");
        }

        this.medicineTable.refresh();
    }

    // click logout button , go to the login page
    public void logoutAction(ActionEvent e) throws IOException {
        Stage stage = (Stage)this.logout.getScene().getWindow();
        FXMLLoader l = new FXMLLoader();
        l.setLocation(this.getClass().getResource("/View/Login.fxml"));
        stage.setTitle("Login page");
        Scene scene = new Scene((Parent)l.load());
        stage.setScene(scene);
        stage.show();
    }

    //click exitSystem Button , need to save log to a file
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
