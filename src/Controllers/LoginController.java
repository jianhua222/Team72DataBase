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
    private Button loginButton;
    @FXML
    private Button registerButton;
    //public LoginController(){}
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private void loginButtonpressed(){
        boolean successLogin = UserManagement.login(this.usernameField.getText(), this.passwordField.getText());
        if (successLogin){
            System.out.println("Successfully Login");
            try{
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/MainPage.fxml"));
                Stage primaryStage = (Stage)loginButton.getScene().getWindow();
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
                String errorText = "Login failed, please try again";
                controller.setLabel(errorText);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch (IOException e){

            }
        }
    }
    @FXML
    private void registerButtonpressed(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/Register.fxml"));
            Stage primaryStage = (Stage) registerButton.getScene().getWindow();
            primaryStage.setTitle("Register");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }catch (IOException e){

        }
    }
}
