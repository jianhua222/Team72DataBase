package Models;

import Models.User;

import java.sql.*;

/**
 * Created by Allen on 11/6/2016.
 */
public class UserManagement {
    public User currentUser;
    public static boolean login(String user_name, String password){
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
            ps = con.prepareStatement( "SELECT user_name, password FROM User WHERE user_name = ? AND password = ?" );
            // 使用问号作为参数的标示

            ps.setString(1, user_name);
            ps.setString(2, password );

            // 结果集
            rs = ps.executeQuery();

            if ( rs.next() ) {
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
