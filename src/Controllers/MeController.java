package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Qi Zheng on 11/22/2016.
 */
public class MeController {


    @FXML
    private Button backButton;
    @FXML
    private Hyperlink editProfile;
    @FXML
    private Hyperlink myApplication;

    @FXML
    private void backButtonpressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/MainPage.fxml"));
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setTitle("Me");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }

    }

    @FXML
    private void editProfilePressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/EditProfile.fxml"));
            Stage primaryStage = (Stage) editProfile.getScene().getWindow();
            primaryStage.setTitle("Me");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }

    }

    @FXML
    private void myApplicationPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/MyApplication.fxml"));
            Stage primaryStage = (Stage) myApplication.getScene().getWindow();
            primaryStage.setTitle("Me");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }

    }
}
