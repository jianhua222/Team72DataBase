package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Models.UserManagement;


/**
 * Created by Allen on 11/6/2016.
 */
public class RegisterController {
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmField;
    public RegisterController(){}
    @FXML
    private void createButtonpressed(){
        UserManagement.register(this.usernameField.getText(), this.emailField.getText(), this.passwordField.getText());
    }

    @FXML
    private void cancelButtonpressed(){

    }
}
