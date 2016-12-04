package Controllers;

import Models.PopulatingComboDownBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
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
public class AdminAddProjController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField advisorField;
    @FXML
    private TextField emailField;
    @FXML
    private TextArea desArea;
    @FXML
    private Button categoryButton;
    @FXML
    private ComboBox<String> designationBox;
    @FXML
    private TextField numberField;
    @FXML
    private ComboBox<String> majorBox;
    @FXML
    private ComboBox<String> yearBox;
    @FXML
    private ComboBox<String> departmentBox;
    @FXML
    private Button backButton;
    @FXML
    private void initialize() {
        designationBox.getItems().addAll(PopulatingComboDownBox.populateDesignationBox());
        ArrayList<String> tem1 = PopulatingComboDownBox.populateYearBox();
        tem1.add(0,"No Requirement");
        yearBox.getItems().addAll(tem1);
        ArrayList<String> tem2 = PopulatingComboDownBox.populateMajorBox();
        tem2.add(0,"No Requirement");
        majorBox.getItems().addAll(tem2);
        departmentBox.getItems().addAll(PopulatingComboDownBox.populateDepartmentBox());
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
        String name = nameField.getText();
        String advisor = advisorField.getText();
        String email = emailField.getText();
        String des = desArea.getText();
        ArrayList<String> categories = CategorySelectController.selectedCategories;
        String designation = designationBox.getValue();

        String major = majorBox.getValue();
        String year = yearBox.getValue();
        String department = departmentBox.getValue();
        if(name.length()==0||advisor.length()==0||email.length()==0
                ||des.length()==0||categories.size()==0
                || designation ==null || numberField.getText().length()==0|| major==null|| year== null ||department==null){
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
                ps = con.prepareStatement( "INSERT INTO Project (pname, pnum_student, adv_name, adv_email, discription, Desig_name) VALUES (?,?,?,?,?,?)" );
                ps.setString(1, name);
                ps.setInt(2, number);
                ps.setString(3, advisor);
                ps.setString(4, email);
                ps.setString(5, des);
                ps.setString(6, designation);
                System.out.println(ps.toString());
                ps.executeUpdate();

                String tem1 = "";
                for (int i=0; i<categories.size();i++){
                    tem1=tem1+"('"+name+"','"+categories.get(i)+"'),";
                }
                tem1=tem1.substring(0,tem1.length()-1);
                ps = con.prepareStatement( "INSERT INTO Proj_is_Category (proj_name, category_name) VALUES " + tem1 );
                ps.executeUpdate();
                if (!year.equals("No Requirement")) {
                    ps = con.prepareStatement("INSERT INTO Requirement (proj_name, proj_requirements) VALUES (?,?)");
                    ps.setString(1, name);
                    ps.setString(2, year);
                    ps.executeUpdate();
                }
                if (!major.equals("No Requirement")) {
                    ps = con.prepareStatement("INSERT INTO Requirement (proj_name, proj_requirements) VALUES (?,?)");
                    ps.setString(1, name);
                    ps.setString(2, major);
                    ps.executeUpdate();
                }
                if (!department.equals("No Requirement")){
                    ArrayList<String> majors = new ArrayList<>();

                    ps = con.prepareStatement("SELECT major_name FROM Major WHERE depart_name=?");
                    ps.setString(1, department);
                    rs = ps.executeQuery();
                    while (rs.next()){
                        majors.add(rs.getString("major_name"));
                    }
                    String tem2 = "";
                    for (int i=0; i<majors.size();i++){
                        tem2=tem2+"('"+name+"','"+majors.get(i)+"'),";
                    }
                    tem2=tem2.substring(0,tem2.length()-1);
                    ps = con.prepareStatement( "INSERT INTO Requirement (proj_name, proj_requirements) VALUES " + tem2 );
                    ps.executeUpdate();
                }


            } catch(Exception e) {
                System.err.println("Exception: 11" + e.getMessage());

            }
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
