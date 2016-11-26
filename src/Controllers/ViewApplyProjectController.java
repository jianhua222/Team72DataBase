package Controllers;

import Models.PopulatingComboDownBox;
import Models.User;
import Models.UserManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;

/**
 * Created by Qi Zheng on 11/26/2016.
 */
public class ViewApplyProjectController {
    @FXML
    private Label projectName;
    @FXML
    private Label projectContent;
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
            String display = "Advisor name:" + adv_name +"("+adv_email+")\n"
                    +"Description: \n" + description+"\n"
                    + "Designation: "+design;
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
}
