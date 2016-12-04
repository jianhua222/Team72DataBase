package Controllers;

import Models.PopulatingComboDownBox;
import Models.User;
import Models.UserManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by Qi Zheng on 11/26/2016.
 */
public class ViewApplyProjectController {
    @FXML
    private Label projectName;
    @FXML
    private Label projectContent;
    @FXML
    private Button backButton;
    @FXML
    private Button applyButton;


    public void initialize(){
        String name = MainPageController.selectedResult.getName();

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
            ps = con.prepareStatement( "SELECT * FROM Project WHERE pname = ?" );
            // 使用问号作为参数的标示

            ps.setString(1, name);

            // 结果集
            rs = ps.executeQuery();
            rs.next();
            String adv_name = rs.getString("adv_name");
            String adv_email = rs.getString("adv_email");
            String description = rs.getString("discription");
            String design = rs.getString("Desig_name");
            Integer numStud = rs.getInt("pnum_student");

            ps = con.prepareStatement( "SELECT category_name FROM Project join Proj_is_Category on pname=proj_name WHERE pname = ?" );
            ps.setString(1, name);
            rs = ps.executeQuery();
            String category = "";
            while(rs.next()){
                category = category +" "+ rs.getString("category_name");
            }
            ps = con.prepareStatement( "SELECT proj_requirements FROM Project join Requirement on pname=proj_name WHERE pname = ?" );
            ps.setString(1, name);
            rs = ps.executeQuery();
            String require = "";
            while(rs.next()){
                require = require +" "+ rs.getString("proj_requirements");
            }
            String display = "Advisor name:" + adv_name +"("+adv_email+")\n"
                    +"Description: \n" + description+"\n"
                    + "Designation: "+design+"\n"
                    + "Category:"+category+"\n"
                    + "Requirements:" +require +"\n"
                    +"Estimated number of students:" +numStud;
            projectName.setText(name);
            projectContent.setText(display);


        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if(con != null)
                    rs.close();
                ps.close();
                con.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void backPressed(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void applyButtonPressed(){
        String name = MainPageController.selectedResult.getName();
        String userName = UserManagement.currentUser.getUser_name();
        Connection con = null;
        PreparedStatement ps = null;
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
            ps = con.prepareStatement( "SELECT * FROM Stud_Apply_Proj WHERE proj_name = ? and stu_username= ?" );
            // 使用问号作为参数的标示

            ps.setString(1, name);
            ps.setString(2,userName);
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat("yy/MM/dd");
            String currentTime = sdf.format(dt);
            System.out.println(ps.toString());
            // 结果集
            rs = ps.executeQuery();
            if( !rs.next()){
                ps2 = con.prepareStatement("insert into Stud_Apply_Proj (proj_name, stu_username, app_date, app_status) values (?,?,?,'pending')");
                System.out.println(ps2.toString());
                ps2.setString(1, name);
                ps2.setString(2, userName);
                ps2.setString(3, currentTime);
                ps2.executeUpdate();
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            }
            else {
                System.out.println("applied");
            }
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
