package Controllers;

import Models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qi Zheng on 12/3/2016.
 */
public class AdminViewApRptController {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Report> result;
    @FXML
    private TableColumn<Report, String> name;
    @FXML
    private TableColumn<Report, Integer> number;
    @FXML
    private TableColumn<Report, Integer> rate;
    @FXML
    private TableColumn<Report, Integer> major;

    @FXML
    private Label info;

    private List<Report> results;
    @FXML
    public void initialize(){

        info.setText(CheckingTotal.check());
        Connection con = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        PreparedStatement ps4 = null;
        PreparedStatement ps5 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_72",
                    "cs4400_Team_72",
                    "mmZwNaCR");
            if(!con.isClosed())
                System.out.println("Successfully connected to " +
                        "MySQL server using TCP/IP...");
            //This will be the first sql statement!

            name.setCellValueFactory(new PropertyValueFactory<Report, String>("name"));
            number.setCellValueFactory(new PropertyValueFactory<Report, Integer>("number"));
            rate.setCellValueFactory(new PropertyValueFactory<Report, Integer>("rate"));
            major.setCellValueFactory(new PropertyValueFactory<Report, Integer>("major"));

            ps1 = con.prepareStatement( " SELECT pname, count(stu_username) AS c FROM Project LEFT JOIN (SELECT proj_name, stu_username From Stud_Apply_Proj) as a on pname=a.proj_name GROUP BY pname ORDER BY pname" );
            ps2 = con.prepareStatement( " SELECT pname, count(stu_username) AS c FROM Project LEFT JOIN (SELECT proj_name, stu_username From Stud_Apply_Proj WHERE app_status='approved') as a on pname=a.proj_name GROUP BY pname ORDER BY pname" );
            ps3 = con.prepareStatement( "select pname, Major_name FROM (select pname, " +
                    "Major_name, count(*)as cmajor FROM Project LEFT JOIN " +
                    "(select proj_name, Major_name FROM Stud_Apply_Proj " +
                    "JOIN User on stu_username=user_name)" +
                    " as a on pname=a.proj_name group by pname,Major_name)" +
                    " as c1 LEFT JOIN (select pname as p1, m2 as m1, " +
                    "count(*)as cmajor1 FROM Project LEFT JOIN " +
                    "(select proj_name as p2, Major_name as m2 " +
                    "FROM Stud_Apply_Proj JOIN User on stu_username=user_name)" +
                    " as b on pname=b.p2 group by p1,m1) as c2 on c1.pname=c2.p1 " +
                    "and c1.cmajor<=c2.cmajor1 group by c1.pname,c1.Major_name having count(*) <=3 ORDER BY pname" );

            results = new ArrayList<>();
            rs1 = ps1.executeQuery();
            rs2 = ps2.executeQuery();
            rs3 = ps3.executeQuery();
            NumberFormat formatter = new DecimalFormat("#0.00");
            ArrayList<ProjectAndMajor> tem = new ArrayList<>();
            while (rs3.next()){
                tem.add(new ProjectAndMajor(rs3.getString("pname"),rs3.getString("Major_name")));
            }
            int temPointer = 0;
            while (rs1.next()&&rs2.next()) {
                String major = "";

                String name = rs1.getString("pname");
                Integer number = rs1.getInt("c");
                Integer approved = rs2.getInt("c");
                while (temPointer<tem.size() && tem.get(temPointer).project.equals(name)){
                    major = major+"/" + tem.get(temPointer).major;
                    temPointer++;
                }

                double rate = 0.00;
                if(number != 0){
                    rate = Double.parseDouble(formatter.format((approved*1.0)/number));
                }

                results.add(new Report(name,number ,rate,major));
            }
            System.out.println(results);
            result.getItems().setAll(results);

        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }

    }
    @FXML
    private void backButtonPressed(){

        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/AdminChooseFunctionality.fxml"));
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setTitle("Me");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }
    }
}
