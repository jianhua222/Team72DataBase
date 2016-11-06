package sample.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
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
            sql= "INSERT INTO User (user_name, password, stuflag, GT_email, year, Major_name, Adminflag)" +
                    "VALUES ('Huajian', '12341234', True, 'ff@gatech.edu', 'junior', 'CS', False)";
            stmt.executeUpdate(sql);

            sql= "INSERT INTO User (user_name, password, stuflag, GT_email, year, Major_name, Adminflag)" +
                    "VALUES ('Runqi', '12341234', True, 'dd@gatech.edu', 'junior', 'CS', False)";
            stmt.executeUpdate(sql);

            sql= "INSERT INTO User (user_name, password, stuflag, GT_email, year, Major_name, Adminflag)" +
                    "VALUES ('Zhengqi', '12341234', True, 'gg@gatech.edu', 'junior', 'CS', False)";
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
        launch(args);
    }
}
