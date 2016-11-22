package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Models.UserManagement;
import javafx.stage.Stage;

import java.io.IOException;


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
        if (this.passwordField.getText().equals(this.confirmField.getText())){
            UserManagement.register(this.usernameField.getText(), this.emailField.getText(), this.passwordField.getText());
            try{
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/MainPage.fxml"));
                Stage primaryStage = (Stage)createButton.getScene().getWindow();
                primaryStage.setTitle("MainPage");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();
            }
            catch (IOException e){

            }
        }else {
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main/Error.fxml"));
                root = loader.load();
                //Parent root = FXMLLoader.load(getClass().getResource("Main/Register.fxml"));
                ErrorController controller =  loader.<ErrorController>getController();
                String errorText = "password doesn't match, please check again...";
                controller.setLabel(errorText);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch (IOException e){

            }
        }
    }

    @FXML
    private void cancelButtonpressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/Login.fxml"));
            Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }

    }
}
