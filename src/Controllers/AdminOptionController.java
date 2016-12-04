package Controllers;

import Models.UserManagement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Ranchy on 12/4/2016.
 */
public class AdminOptionController {
    @FXML
    private ComboBox<String> dropDown;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;
    @FXML
    public void initialize(){
        ArrayList<String> tem = new ArrayList<>();
        tem.add("approve");
        tem.add("reject");
        dropDown.getItems().addAll(tem);
    }
    @FXML
    public void submitButtonPressed(){

        if(dropDown.getValue() == null){
            try{
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminViewApplication.fxml"));
                Stage primaryStage = (Stage) submitButton.getScene().getWindow();
                primaryStage.setTitle("Me");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();
            }
            catch (IOException e){

            }
        }
        else if(dropDown.getValue().equals("approve")){
            Connection con = null;
            PreparedStatement ps = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_72",
                        "cs4400_Team_72",
                        "mmZwNaCR");
                if(!con.isClosed())
                    System.out.println("Successfully connected to " +
                            "MySQL server using TCP/IP...");
                //This will be the first sql statement!
                ps = con.prepareStatement( "UPDATE Stud_Apply_Proj SET app_status = ? where stu_username = ? and proj_name = ?" );
                ps.setString(1, "approved");
                ps.setString(2, AdminViewApplicationController.selectedResult.getStudent());
                ps.setString(3, AdminViewApplicationController.selectedResult.getProject());
                ps.executeUpdate();


            } catch(Exception e) {
                System.err.println("Exception: 11" + e.getMessage());

            }
            try{
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminViewApplication.fxml"));
                Stage primaryStage = (Stage) submitButton.getScene().getWindow();
                primaryStage.setTitle("Me");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();
            }
            catch (IOException e){

            }
        }
        else {
            Connection con = null;
            PreparedStatement ps = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_72",
                        "cs4400_Team_72",
                        "mmZwNaCR");
                if(!con.isClosed())
                    System.out.println("Successfully connected to " +
                            "MySQL server using TCP/IP...");
                //This will be the first sql statement!
                ps = con.prepareStatement( "UPDATE Stud_Apply_Proj SET app_status = ? where stu_username = ? and proj_name = ?" );
                ps.setString(1, "rejected");
                ps.setString(2, AdminViewApplicationController.selectedResult.getStudent());
                ps.setString(3, AdminViewApplicationController.selectedResult.getProject());
                ps.executeUpdate();


            } catch(Exception e) {
                System.err.println("Exception: 11" + e.getMessage());

            }
            try{
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminViewApplication.fxml"));
                Stage primaryStage = (Stage) submitButton.getScene().getWindow();
                primaryStage.setTitle("Me");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();
            }
            catch (IOException e){

            }
        }
    }
    @FXML
    private void backButtonPressed(){

        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminViewApplication.fxml"));
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setTitle("Me");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }
    }
}
