package Models;

import Controllers.MainPageController;
import Models.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Allen on 11/6/2016.
 */
public class UserManagement {
    public static User currentUser;
    public static boolean login(String user_name, String password){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String GT_email;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_72",
                    "cs4400_Team_72",
                    "mmZwNaCR");
            if(!con.isClosed())
                System.out.println("Successfully connected to " +
                        "MySQL server using TCP/IP...");
            //This will be the first sql statement!
            ps = con.prepareStatement( "SELECT user_name, GT_email, password,UserType FROM User WHERE user_name = ? AND password = ?" );
            // 使用问号作为参数的标示

            ps.setString(1, user_name);
            ps.setString(2, password );

            // 结果集
            rs = ps.executeQuery();

            if ( rs.next() ) {
                GT_email = rs.getString("GT_email");
                String userType = rs.getString("UserType");
                currentUser = new User(user_name, GT_email, password);
                currentUser.setUserType(userType);
                return true;
            }else {
                return false;
            }

        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return false;
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
    public static boolean checkingRequirement(){
        String projectName = MainPageController.selectedResult.getName();
        String userName = currentUser.getUser_name();
        ArrayList<String> requirements = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
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
            if (major_name.equals(null)){
                return false;
            }
            String year = rs.getString("year");
            requirements.add(major_name);
            requirements.add(year);
            ps = con.prepareStatement( "select pname, count(major_name)as c from" +
                    " Project LEFT JOIN(select proj_name,major_name " +
                    "from Requirement LEFT JOIN Major on proj_requirements=major_name)" +
                    "as a on pname=a.proj_name where pname= ? group by proj_name having c=0" );
            ps.setString(1, projectName);
            rs1 = ps.executeQuery();
            boolean flagmajor;
            if (rs1.next()){
                flagmajor=true;
            }else {
                flagmajor = false;
            }
            ps = con.prepareStatement( "select pname,count(proj_name)as c from" +
                            " Project LEFT JOIN (select proj_name from Requirement" +
                            " where proj_requirements='Fresh man' or proj_requirements='Sophomore'" +
                            " or proj_requirements='Junior' or proj_requirements='Senior' )" +
                            " as a on pname=a.proj_name where pname= ? group by pname having c=0" );
            ps.setString(1, projectName);
            rs2 = ps.executeQuery();
            boolean flagyear;
            if (rs2.next()){
                flagyear=true;
            }else {
                flagyear = false;
            }
            if (flagmajor&&flagyear){
                return true;
            }
            else if (flagmajor){
                ps = con.prepareStatement("select proj_name from Requirement where proj_name=? and proj_requirements=?");
                ps.setString(1, projectName);
                ps.setString(2, year);

                rs = ps.executeQuery();
                if (rs.next()){
                    return true;
                }else {
                    return false;
                }
            }
            else if (flagyear){
                ps = con.prepareStatement("select proj_name from Requirement where proj_name=? and proj_requirements=?");
                ps.setString(1, projectName);
                ps.setString(2, major_name);
                rs = ps.executeQuery();
                if (rs.next()){
                    return true;
                }else {
                    return false;
                }
            }
            else {
                String temreq = "";
                if (requirements.size() != 0) {
                    for (int i = 0; i < requirements.size(); i++) {
                        temreq = temreq + " pname in (select pname from Project" +
                                " join Requirement on pname=proj_name" +
                                " where proj_requirements='" + requirements.get(i) + "') and";
                    }

                }
                String temporj = "select pname from Project where pname='" + projectName + "' and " + temreq;
                temporj = temporj.substring(0, temporj.length() - 3);
                ps = con.prepareStatement(temporj);
                System.out.println(ps.toString());
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }


        return false;

    }
    public static void register(String user_name, String GT_email,String password){
        Connection con = null;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_72",
                    "cs4400_Team_72",
                    "mmZwNaCR");
            if(!con.isClosed())
                System.out.println("Successfully connected to " +
                        "MySQL server using TCP/IP...");
            //This will be the first sql statement!
            stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql;
            sql= "INSERT INTO User (user_name, password, UserType, GT_email) " +
                    "VALUES ('"+user_name+"', '"+password+"', "+"'Student', '"+GT_email+"' );";
            //System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
            //Clean-up environment
            stmt.close();
            con.close();
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if(con != null)
                    con.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
