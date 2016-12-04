package Controllers;

import Models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
 * Created by Qi Zheng on 11/26/2016.
 */
public class MyApplicationController {
    @FXML
    private Button backToMe;
    @FXML
    private TableColumn<Application,String> date;
    @FXML
    private TableColumn<Application,String> name;
    @FXML
    private TableColumn<Application,String> status;
    @FXML
    private TableView<Application> result;

    private List<Application> results;

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
            ps1 = con.prepareStatement( "SELECT proj_name, app_date, app_status FROM Stud_Apply_Proj where stu_username=?" );
            // 使用问号作为参数的标示
            ps1.setString(1, UserManagement.currentUser.getUser_name());
            //ps1.setString(2, password );
            // 结果集
            date.setCellValueFactory(new PropertyValueFactory<Application, String>("date"));
            name.setCellValueFactory(new PropertyValueFactory<Application, String>("name"));
            status.setCellValueFactory(new PropertyValueFactory<Application, String>("status"));
            results = new ArrayList<>();
            rs = ps1.executeQuery();
            while (rs.next()) {
                results.add(new Application(rs.getString("app_date"),rs.getString("proj_name"),rs.getString("app_status")));
            }
            result.getItems().setAll(results);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }
    }
    @FXML
    private void backToMePressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/Me.fxml"));
            Stage primaryStage = (Stage) backToMe.getScene().getWindow();
            primaryStage.setTitle("My Application");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }

    }
}
