package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Allen on 11/22/2016.
 */
public class PopulatingComboDownBox {
    public static ArrayList<String> populateDesignationBox(){
            ArrayList<String> tem = new ArrayList<>();
            Connection con = null;
            PreparedStatement ps1 = null;
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
                ps1 = con.prepareStatement( "SELECT DISTINCT Desig_name FROM Course UNION SELECT DISTINCT Desig_name FROM Project;" );
                // 使用问号作为参数的标示
                //ps.setString(1, user_name);
                //ps.setString(2, password );
                // 结果集

                rs = ps1.executeQuery();

                while (rs.next()) {
                    tem.add(rs.getString("Desig_name"));
                }
                System.out.print(tem);
            } catch(Exception e) {
                System.err.println("Exception: 11" + e.getMessage());

            }
        return tem;
    }
    public static ArrayList<String> populateYearBox(){
        ArrayList<String> tem = new ArrayList<>();
        tem.add("Fresh Man");
        tem.add("Sophomore");
        tem.add("Junior");
        tem.add("Senior");
        return tem;
    }
    public static ArrayList<String> populateCategoryBox(){
        ArrayList<String> tem = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps1 = null;
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
            ps1 = con.prepareStatement( "SELECT catg_name FROM Category;" );
            // 使用问号作为参数的标示
            //ps.setString(1, user_name);
            //ps.setString(2, password );
            // 结果集

            rs = ps1.executeQuery();

            while (rs.next()) {
                tem.add(rs.getString("catg_name"));
            }
            System.out.print(tem);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }
        return tem;
    }
    public static ArrayList<String> populateMajorBox(){
        ArrayList<String> tem = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps1 = null;
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
            ps1 = con.prepareStatement( "SELECT major_name FROM Major;" );
            // 使用问号作为参数的标示
            //ps.setString(1, user_name);
            //ps.setString(2, password );
            // 结果集

            rs = ps1.executeQuery();

            while (rs.next()) {
                tem.add(rs.getString("major_name"));
            }
            System.out.print(tem);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());
        }
        return tem;
    }
    public static ArrayList<String> populateDepartmentBox(){
        ArrayList<String> tem = new ArrayList<>();
        tem.add("No Requirement");
        Connection con = null;
        PreparedStatement ps1 = null;
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
            ps1 = con.prepareStatement( "SELECT depart_name FROM department;" );
            // 使用问号作为参数的标示
            //ps.setString(1, user_name);
            //ps.setString(2, password );
            // 结果集

            rs = ps1.executeQuery();

            while (rs.next()) {
                tem.add(rs.getString("depart_name"));
            }
            System.out.print(tem);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());
        }
        return tem;
    }
    public static HashMap<String,String> populateResult() {
        HashMap<String,String> tem = new HashMap<>();
        Connection con;
        PreparedStatement ps1;
        PreparedStatement ps2;
        ResultSet rs;
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

            while (rs.next()) {
                tem.put(rs.getString("cname"),"Course");
            }
            rs = ps2.executeQuery();
            while (rs.next()) {
                tem.put(rs.getString("pname"),"Project");
            }

        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }
        return tem;
    }
}
