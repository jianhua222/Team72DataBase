package Controllers;

import Models.UserManagement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Allen on 11/6/2016.
 */
public class LoginController {
    @FXML
    private Button registerButton;
    //public LoginController(){}
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private void loginButtonpressed(){
        UserManagement.login(this.usernameField.getText(), this.passwordField.getText());
    }
    @FXML
    private void registerButtonpressed(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/Register.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("Main/Register.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){

        }
    }
}
