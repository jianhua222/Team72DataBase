package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Qi on 11/22/2016.
 */
public class MeController {


    @FXML
    private Button backButton;
    @FXML
    private void backButtonpressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/MainPage.fxml"));
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setTitle("Main");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }

    }
}
