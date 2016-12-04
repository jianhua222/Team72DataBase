package Controllers;

import Models.PopularProject;
import Models.Report;
import Models.Result;
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
public class AdminViewApRptController {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Report> result;
    @FXML
    private TableColumn<Report, String> name;
    @FXML
    private TableColumn<Report, Integer> number;
    @FXML
    private TableColumn<Report, Integer> rate;
    @FXML
    private TableColumn<Report, Integer> major;

    private List<Report> results;
    @FXML
    public void initialize(){


        Connection con = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_72",
                    "cs4400_Team_72",
                    "mmZwNaCR");
            if(!con.isClosed())
                System.out.println("Successfully connected to " +
                        "MySQL server using TCP/IP...");
            //This will be the first sql statement!

            name.setCellValueFactory(new PropertyValueFactory<Report, String>("name"));
            number.setCellValueFactory(new PropertyValueFactory<Report, Integer>("number"));
            rate.setCellValueFactory(new PropertyValueFactory<Report, Integer>("rate"));
            major.setCellValueFactory(new PropertyValueFactory<Report, Integer>("major"));

            ps1 = con.prepareStatement( "SELECT proj_name, COUNT(std_username) AS c FROM Stud_Apply_Proj JOIN Project on pname=proj_name GROUP BY proj_name" );
            ps2 = con.prepareStatement( "SELECT proj_name, COUNT(std_username) AS c FROM Stud_Apply_Proj JOIN Project on pname=proj_name WHERE app_status='approved' GROUP BY proj_name" );
            ps3 = con.prepareStatement( "SELECT proj_name, Major_name FROM(SELECT proj_name, Major_name FROM Stud_Apply_Proj JOIN User on stu_username=user_name) JOIN Project on pname=proj_name GROUP BY proj_name " );

            results = new ArrayList<>();
            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                results.add(new Report("1",1 ,"1","1"));
            }
            System.out.println(results);
            result.getItems().setAll(results);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

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
