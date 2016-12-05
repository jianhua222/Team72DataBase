package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.awt.SystemColor.info;

/**
 * Created by Ranchy on 12/4/2016.
 */
public class CheckingTotal {
    public static String check(){
        String tem = "";
        Connection con = null;
        PreparedStatement ps4 = null;
        PreparedStatement ps5 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;

        try {
            System.out.println("here");
            System.out.println("here");
            System.out.println("here");System.out.println("here");

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_72",
                    "cs4400_Team_72",
                    "mmZwNaCR");
            if(!con.isClosed())
                System.out.println("Successfully connected to " +
                        "MySQL server using TCP/IP...");
            //This will be the first sql statement!
            Integer total1 = 0;
            Integer total2 =0;

            ps4 = con.prepareStatement("select count(*) as c from Stud_Apply_Proj");
            ps5 = con.prepareStatement("select count(*) as c from Stud_Apply_Proj where app_status='approved' ");
            rs4 = ps4.executeQuery();
            rs4.next();
            total1 = rs4.getInt("c");
            rs5 = ps5.executeQuery();
            rs5.next();
            total2 = rs5.getInt("c");
            tem = ""+total1+" applications in total, accepted "+total2+" applications";
            System.out.println(tem);
            System.out.println(tem);
            System.out.println(tem);
            System.out.println(tem);


        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());
        }
        return tem;
    }
}
