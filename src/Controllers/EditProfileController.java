package Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * Created by Qi Zheng on 11/26/2016.
 */
public class EditProfileController {

    @FXML
    private Button backToMe;

    @FXML
    private void backToMepressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/Me.fxml"));
            Stage primaryStage = (Stage) backToMe.getScene().getWindow();
            primaryStage.setTitle("Edit Profile");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }

    }
}