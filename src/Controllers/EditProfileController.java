package Controllers;
import Models.PopulatingComboDownBox;
import Models.UserManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Created by Qi Zheng on 11/26/2016.
 */
public class EditProfileController {

    @FXML
    private ComboBox editmajorBox;
    @FXML
    private ComboBox edityearBox;
    @FXML
    private Label departmentName;

    public void initialize(){
        edityearBox.getItems().addAll(PopulatingComboDownBox.populateYearBox());
        editmajorBox.getItems().addAll(PopulatingComboDownBox.populateMajorBox());
        Connection con = null;
        PreparedStatement ps = null;
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
            ps = con.prepareStatement( "SELECT major_name, year FROM User where user_name = ?" );
            ps.setString(1, UserManagement.currentUser.getUser_name());
            rs = ps.executeQuery();
            rs.next();
            String major_name = rs.getString("major_name");
            String year = rs.getString("year");
            editmajorBox.getSelectionModel().select(major_name);
            edityearBox.getSelectionModel().select(year);
            ps = con.prepareStatement( "SELECT depart_name FROM Major where major_name = ?" );
            // 使用问号作为参数的标示
            ps.setString(1, major_name);
            //ps.setString(2, password );
            // 结果集
            rs = ps.executeQuery();
            if (rs.next()){
                String tem = rs.getString("depart_name");
                departmentName.setText(tem);
            }else {
                departmentName.setText("");
            }


        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }


    }

    @FXML
    private Button backToMe;

    @FXML
    private void backToMepressed(){
        try{
            if (!editmajorBox.getSelectionModel().isEmpty() ) {
                Connection con = null;
                PreparedStatement ps = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_72",
                            "cs4400_Team_72",
                            "mmZwNaCR");
                    if (!con.isClosed())
                        System.out.println("Successfully connected to " +
                                "MySQL server using TCP/IP...");
                    //This will be the first sql statement!
                    ps = con.prepareStatement("UPDATE User SET Major_name = ? where user_name = ? ");
                    // 使用问号作为参数的标示
                    ps.setString(1, editmajorBox.getSelectionModel().getSelectedItem().toString());
                    ps.setString(2, UserManagement.currentUser.getUser_name());
                    // 结果集
                    ps.executeUpdate();
                } catch (Exception e) {
                    System.err.println("Exception: 11" + e.getMessage());

                }
            }
            if (!edityearBox.getSelectionModel().isEmpty() ){
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
                    ps = con.prepareStatement("UPDATE User SET year = ? where user_name = ? ");
                    // 使用问号作为参数的标示
                    ps.setString(1, edityearBox.getSelectionModel().getSelectedItem().toString());
                    ps.setString(2, UserManagement.currentUser.getUser_name());
                    // 结果集
                    ps.executeUpdate();
                } catch(Exception e) {
                    System.err.println("Exception: 11" + e.getMessage());

                }
            }
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