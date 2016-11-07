package Controllers;

import Models.UserManagement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Allen on 11/6/2016.
 */
public class ErrorController {
    @FXML
    private Label errorText;
    @FXML
    private Button okButton;
    public void setLabel(String errorText){
        this.errorText.setText(errorText);
    }
    @FXML
    private void okButtonpressed(){
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
