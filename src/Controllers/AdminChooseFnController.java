package Controllers;

import Models.UserManagement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Qi Zheng on 12/3/2016.
 */

public class AdminChooseFnController {
    @FXML
    private Hyperlink viewAp;
    @FXML
    private Hyperlink viewPp;
    @FXML
    private Hyperlink viewAprt;
    @FXML
    private Hyperlink addProj;
    @FXML
    private Hyperlink addCourse;
    @FXML
    private Button logout;


    @FXML
    private void adminViewApPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminViewApplication.fxml"));
            Stage primaryStage = (Stage) viewAp.getScene().getWindow();
            primaryStage.setTitle("Choose Functionality");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){
        }
    }

    @FXML
    private void adminViewPpPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminViewPopularProject.fxml"));
            Stage primaryStage = (Stage) viewPp.getScene().getWindow();
            primaryStage.setTitle("Choose Functionality");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){
        }
    }

    @FXML
    private void adminViewAprtPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminViewApplicationReport.fxml"));
            Stage primaryStage = (Stage) viewAprt.getScene().getWindow();
            primaryStage.setTitle("Choose Functionality");
            primaryStage.setScene(new Scene(root, 800, 400));
            primaryStage.show();
        }
        catch (IOException e){
        }
    }

    @FXML
    private void adminAddProjPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminAddProject.fxml"));
            Stage primaryStage = (Stage) addProj.getScene().getWindow();
            primaryStage.setTitle("Choose Functionality");
            primaryStage.setScene(new Scene(root, 600, 650));
            primaryStage.show();
        }
        catch (IOException e){
        }
    }

    @FXML
    private void addCoursePressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminAddCourse.fxml"));
            Stage primaryStage = (Stage) addCourse.getScene().getWindow();
            primaryStage.setTitle("Choose Functionality");
            primaryStage.setScene(new Scene(root, 600, 650));
            primaryStage.show();
        }
        catch (IOException e){
        }
    }

    @FXML
    private void LogoutPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/Login.fxml"));
            Stage primaryStage = (Stage) logout.getScene().getWindow();
            primaryStage.setTitle("Choose Functionality");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
            UserManagement.currentUser = null;
        }
        catch (IOException e){
        }

    }
}
