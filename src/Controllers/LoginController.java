package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by Allen on 11/6/2016.
 */
public class LoginController {
    @FXML
    private Button registerButton;
    private void registerButtonpressed(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        }catch (IOException e){

        }
    }
}
