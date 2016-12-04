package Controllers;


import Models.AdminViewProjectResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qi Zheng on 12/3/2016.
 */
public class AdminViewApplicationController {
    @FXML
    private TableView<AdminViewProjectResult> result;
    @FXML
    private TableColumn<AdminViewProjectResult, String> project;
    @FXML
    private TableColumn<AdminViewProjectResult, String> major;
    @FXML
    private TableColumn<AdminViewProjectResult, String> year;
    @FXML
    private TableColumn<AdminViewProjectResult, String> status;
    @FXML
    private Button backButton;

    private List<AdminViewProjectResult> results;

    public static AdminViewProjectResult  selectedResult;

    @FXML
    public void initialize(){


        Connection con = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_72",
                    "cs4400_Team_72",
                    "mmZwNaCR");
            if(!con.isClosed())
                System.out.println("Successfully connected to " +
                        "MySQL server using TCP/IP...");
            //This will be the first sql statement!
            ps1 = con.prepareStatement( "SELECT proj_name, stu_username, Major_name, year, app_status FROM Stud_Apply_Proj join User on stu_username=user_name" );
            project.setCellValueFactory(new PropertyValueFactory<AdminViewProjectResult, String>("project"));
            major.setCellValueFactory(new PropertyValueFactory<AdminViewProjectResult, String>("major"));
            year.setCellValueFactory(new PropertyValueFactory<AdminViewProjectResult, String>("year"));
            status.setCellValueFactory(new PropertyValueFactory<AdminViewProjectResult, String>("status"));
            results = new ArrayList<>();
            rs = ps1.executeQuery();
            while (rs.next()) {
                results.add(new AdminViewProjectResult(rs.getString("proj_name"),rs.getString("Major_name"),rs.getString("year"),rs.getString("app_status"),rs.getString("stu_username")));
            }
            System.out.println(results);
            result.getItems().setAll(results);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }
    }
    @FXML
    private void resultPressed(){
        selectedResult = result.getSelectionModel().getSelectedItem();
        if (selectedResult.getStatus().equals("pending")){
            try{
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminOption.fxml"));
                Stage primaryStage = (Stage) result.getScene().getWindow();
                primaryStage.setTitle("MainPage");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();
            }
            catch (IOException e){

            }
        }
        else {
            System.out.println("project has been " + selectedResult.getStatus());
        }
    }
    @FXML
    private void backButtonPressed(){

        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminChooseFunctionality.fxml"));
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setTitle("Me");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }
    }


}
