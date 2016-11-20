package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Ranchy on 11/20/2016.
 */
public class MainPageController {
    @FXML
    private ListView<String> listView;

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
            ps1 = con.prepareStatement( "SELECT cname FROM Course" );
            ps2 = con.prepareStatement( "SELECT pname FROM Project" );
            // 使用问号作为参数的标示

            //ps.setString(1, user_name);
            //ps.setString(2, password );

            // 结果集
            System.out.println("here1");
            rs = ps1.executeQuery();
            System.out.println(rs);
            ObservableList<String> tem = FXCollections.observableArrayList();
            while (rs.next()) {

                tem.add(rs.getString("cname"));
                System.out.println(tem);

            }
            System.out.println("here4");
            rs = ps2.executeQuery();
            while (rs.next()) {

                tem.add(rs.getString("pname"));
                System.out.println(tem);
            }
            System.out.println("here1");
            if(tem == null){
                System.out.println("a");
            }

            listView.setItems(tem);


        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        } finally {
            try {
                if(con != null)
                    rs.close();
                ps1.close();
                ps2.close();
                con.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
