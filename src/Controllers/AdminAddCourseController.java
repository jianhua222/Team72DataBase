package Controllers;

import Models.PopulatingComboDownBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Qi Zheng on 12/3/2016.
 */
public class AdminAddCourseController {
    @FXML
    private TextField cnumberField;
    @FXML
    private TextField nameFiled;
    @FXML
    private TextField instructorField;
    @FXML
    private ComboBox<String> designationBox;

    @FXML
    private Button categoryButton;

    @FXML
    private Button backButton;
    @FXML
    private Button submitButton;
    @FXML
    private TextField numberField;
    @FXML
    private void initialize() {
        designationBox.getItems().addAll(PopulatingComboDownBox.populateDesignationBox());

    }
    @FXML
    private void categoryButtonPressed(){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main/CategorySelect.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){

        }
    }
    @FXML
    private void submitButtonPressed(){
        String cnumber = cnumberField.getText();
        String name = nameFiled.getText();
        String instructor = instructorField.getText();


        ArrayList<String> categories = CategorySelectController.selectedCategories;
        String designation = designationBox.getValue();



        if(name.length()==0||cnumber.length()==0||instructor.length()==0
                ||categories.size()==0
                || designation ==null || numberField.getText().length()==0){
            System.out.println("enter all information");
        }
        else {

            Integer number = Integer.parseInt(numberField.getText());

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
                ps = con.prepareStatement( "INSERT INTO Course (cnumber, cname, instructor, cnum_student, Desig_name) VALUES (?,?,?,?,?)" );
                ps.setString(1, cnumber);
                ps.setString(2, name);
                ps.setString(3, instructor);
                ps.setInt(4, number);
                ps.setString(5, designation);
                System.out.println(ps.toString());
                ps.executeUpdate();

                String tem1 = "";
                for (int i=0; i<categories.size();i++){
                    tem1=tem1+"('"+cnumber+"','"+categories.get(i)+"'),";
                }
                tem1=tem1.substring(0,tem1.length()-1);
                ps = con.prepareStatement( "INSERT INTO Course_is_Category (course_num, category_name) VALUES " + tem1 );
                ps.executeUpdate();
            } catch(Exception e) {
                System.err.println("Exception: 11" + e.getMessage());

            }
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
