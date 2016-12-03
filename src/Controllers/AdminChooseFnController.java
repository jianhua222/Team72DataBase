package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
}
