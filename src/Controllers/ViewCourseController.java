package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by Allen on 12/3/2016.
 */
public class ViewCourseController {
    @FXML
    private Button backButton;
    @FXML
    private Label title;
    @FXML
    private Label content;
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
            ps = con.prepareStatement( "SELECT * FROM Course WHERE cname = ?" );
            // 使用问号作为参数的标示

            ps.setString(1, name);

            // 结果集
            rs = ps.executeQuery();
            rs.next();
            String cnumber = rs.getString("cnumber");
            String instructor = rs.getString("instructor");
            String design = rs.getString("Desig_name");
            Integer numStud = rs.getInt("cnum_Student");

            ps = con.prepareStatement( "SELECT * FROM Course join Course_is_Category on cnumber=course_num WHERE cname = ?" );
            ps.setString(1, name);
            rs = ps.executeQuery();
            String category="";
            while (rs.next()) {

               category = category + " "+ rs.getString("category_name");
            }
            String display = "Course Name: "+name+"\n"+"Instructor:" +instructor+"\n"
                                +"Designation:"+design+"\n"+"Category:"+category+"\n"
                                +"Estimated number of students:" +numStud;
            title.setText(cnumber);
            content.setText(display);


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
}
