package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Qi Zheng on 12/3/2016.
 */
public class AdminAddCourseController {
    @FXML
    private Button categoryButton;

    @FXML
    private void categoryButtonPressed(){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main/CategorySelect.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){

        }
    }


}
