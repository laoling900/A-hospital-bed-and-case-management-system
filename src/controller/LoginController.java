package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

public class LoginController {
    public static int STAFFID = 0;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    public LoginController() {
    }

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if (!this.usernameTextField.getText().isBlank() && !this.enterPasswordField.getText().isBlank()) {
            this.validateLogin();
        } else {
            this.loginMessageLabel.setText("Pls enter username and password");
        }

    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage)this.cancelButton.getScene().getWindow();
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

    public void validateLogin() throws IOException {
        AbleCareHome careHome = new AbleCareHome();
        careHome.readStaff();
        ArrayList<Staff> staffList = careHome.getStaffList();
        String id = this.usernameTextField.getText();
        int iid = Integer.parseInt(id);
        String password = this.enterPasswordField.getText();
        Staff loginStaff = null;
        boolean loginSucessful = false;

        for(int i = 0; i < staffList.size(); ++i) {
            if (((Staff)staffList.get(i)).getId() == iid && ((Staff)staffList.get(i)).getPassword().equals(password)) {
                this.loginMessageLabel.setText("Congrats!");
                loginStaff = (Staff)staffList.get(i);
                loginSucessful = true;
            }
        }

        STAFFID = iid;
        if (!loginSucessful) {
            this.loginMessageLabel.setText("Invalid login. pls try again");
        } else if (loginStaff instanceof Manager) {
            this.toNewView(this.loginButton, "/View/ManagerPage.fxml", "Manager menu");
        } else if (loginStaff instanceof Doctor) {
            this.toNewView(this.loginButton, "/View/DoctorMenu.fxml", "Doctor menu");
        } else if (loginStaff instanceof Nurse) {
            this.toNewView(this.loginButton, "/View/NurseMenu.fxml", "Nurse Menu");
        }

    }

    public void toNewView(Button buttonName, String location, String title) throws IOException {
        Stage stage = (Stage)buttonName.getScene().getWindow();
        FXMLLoader l = new FXMLLoader();
        l.setLocation(this.getClass().getResource(location));
        stage.setTitle(title);
        Scene scene = new Scene((Parent)l.load());
        stage.setScene(scene);
        stage.show();
    }
}
