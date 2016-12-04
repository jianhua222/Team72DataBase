package Controllers;

import Models.AdminViewProjectResult;
import Models.PopularProject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qi Zheng on 12/3/2016.
 */
public class AdminViewPpController{
    @FXML
    private Button backButton;
    @FXML
    private TableView<PopularProject> result;
    @FXML
    private TableColumn<PopularProject, String> name;
    @FXML
    private TableColumn<PopularProject, Integer> number;

    private List<PopularProject> results;

    @FXML
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
            ps1 = con.prepareStatement( "SELECT proj_name, COUNT(*) AS c FROM Stud_Apply_Proj GROUP BY proj_name ORDER BY c DESC" );
            name.setCellValueFactory(new PropertyValueFactory<PopularProject, String>("name"));
            number.setCellValueFactory(new PropertyValueFactory<PopularProject, Integer>("number"));

            results = new ArrayList<>();
            rs = ps1.executeQuery();
            while (rs.next()) {
                results.add(new PopularProject(rs.getString("proj_name"),rs.getInt("c")));
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
