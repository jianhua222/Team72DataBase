package Controllers;

import Models.PopulatingComboDownBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Ranchy on 11/20/2016.
 */
public class MainPageController {
    @FXML
    private ListView<String> listView;
    @FXML
    private ComboBox designationBox;
    @FXML
    private ComboBox majorBox;
    @FXML
    private ComboBox yearBox;
    @FXML
    private ComboBox categoryBox;
    @FXML
    private Button meButton;

    public void initialize(){
        ArrayList<String> designations = PopulatingComboDownBox.populateDesignationBox();
        designationBox.getItems().addAll(designations);
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
            ps1 = con.prepareStatement( "SELECT cname FROM Course" );
            ps2 = con.prepareStatement( "SELECT pname FROM Project" );
            // 使用问号作为参数的标示
            //ps.setString(1, user_name);
            //ps.setString(2, password );
            // 结果集
            rs = ps1.executeQuery();
            ObservableList<String> tem = FXCollections.observableArrayList();
            while (rs.next()) {
                tem.add(rs.getString("cname"));
            }
            rs = ps2.executeQuery();
            while (rs.next()) {
                tem.add(rs.getString("pname"));
            }
            listView.setItems(tem);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }
    }
    @FXML
    private void meButtonpressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/Me.fxml"));
            Stage primaryStage = (Stage) meButton.getScene().getWindow();
            primaryStage.setTitle("MainPage");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }
    }
}
