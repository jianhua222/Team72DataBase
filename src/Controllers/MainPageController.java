package Controllers;

import Models.PopulatingComboDownBox;
import Models.Project;
import Models.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by Ranchy on 11/20/2016.
 */
public class MainPageController {
    //@FXML
    //private ListView<String> listView;
    @FXML
    private ComboBox designationBox;
    @FXML
    private ComboBox majorBox;
    @FXML
    private ComboBox yearBox;
    @FXML
    private Button selectCategory;
    @FXML
    private Button meButton;
    @FXML
    private TableView<Result> result;
    @FXML
    private TableColumn<Result,String> name;
    @FXML
    private TableColumn<Result, String> type;
    @FXML
    private RadioButton projectButton;
    @FXML
    private RadioButton courseButton;
    @FXML
    private RadioButton bothButton;
    @FXML
    private TextField titleField;

    private List<Result> results;

    public static Result selectedResult;
    public void initialize(){
        designationBox.getItems().addAll(PopulatingComboDownBox.populateDesignationBox());
        yearBox.getItems().addAll(PopulatingComboDownBox.populateYearBox());
        majorBox.getItems().addAll(PopulatingComboDownBox.populateMajorBox());

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
            name.setCellValueFactory(new PropertyValueFactory<Result, String>("name"));
            type.setCellValueFactory(new PropertyValueFactory<Result, String>("type"));
            results = new ArrayList<>();
            rs = ps1.executeQuery();
            while (rs.next()) {
                results.add(new Result(rs.getString("cname"),"Course"));
                //
                // resultView.getItems().add(new Result(rs.getString("cname"),"Course"));

            }
            rs = ps2.executeQuery();
            while (rs.next()) {
                results.add(new Result(rs.getString("pname"), "Project"));
                //resultView.getItems().add(new Result(rs.getString("pname"), "Project"));
            }
            result.getItems().setAll(results);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }
    }
    @FXML
    private void selectCategoryPressed(){
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
    private void meButtonPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/Me.fxml"));
            Stage primaryStage = (Stage) meButton.getScene().getWindow();
            primaryStage.setTitle("MainPage");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

        }
    }
    @FXML
    private void applyButtonPressed(){
        String designation;
        ArrayList<String> requirements = new ArrayList<>();
        String title;
        if(designationBox.getValue()!=null){
           designation = designationBox.getValue().toString();
            System.out.println(designation);
        }
        else {
            designation = "";
        }
        if(yearBox.getValue()!=null){

            requirements.add((String) yearBox.getValue());}

        if(majorBox.getValue()!=null){

            requirements.add((String) majorBox.getValue());
        }
        System.out.println(requirements);
        title = titleField.getText();
        ArrayList<String> categories = CategorySelectController.selectedCategories;
        System.out.println(categories);
        String cs;
        if(categories.size() == 0){
            cs = "";
        }
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

            // 使用问号作为参数的标示
            String temtitle1= "";
            if (title.length()!=0) {
                temtitle1=temtitle1+ " cname='"+title+"' and";
            }
            String temdes1="";
            if (designation.length()!=0) {
                temdes1=temdes1 + " Desig_name='"+designation+"' and";
            }
            String temcate1="";
            if (categories.size()!=0){
                for (int i =0; i<categories.size();i++){
                    temcate1=temcate1 + " cnumber in (select cnumber from Course" +
                            " join Course_is_Category on cnumber=course_num"+
                            " where category_name='" + categories.get(i) +"') and";
                }
            }
            String temcourse = " select cname from Course where "+temtitle1 + temdes1 + temcate1;
            temcourse = temcourse.substring(0,temcourse.length()-3);
            String temtitle= "";
            if (title.length()!=0) {
                temtitle=temtitle+ " pname='"+title+"' and";
            }
            String temdes="";
            if (designation.length()!=0) {
                temdes=temdes + " Desig_name='"+designation+"' and";
            }
            String temreq="";
            if (requirements.size()!=0){
                for (int i =0; i<requirements.size();i++){
                    temreq=temreq + " pname in (select pname from Project" +
                            " join Requirement on pname=proj_name"+
                            " where proj_requirements='" + requirements.get(i)+"') and";
                }

            }
            String temcate="";
            if (categories.size()!=0){
                for (int i =0; i<categories.size();i++){
                    temcate=temcate + " pname in (select pname from Project" +
                            " join Proj_is_Category on pname=proj_name"+
                            " where category_name='" + categories.get(i) +"') and";
                }
            }
            String temporj = "select pname from Project where "+temtitle + temdes + temreq + temcate;
            temporj = temporj.substring(0,temporj.length()-3);
            ps1 = con.prepareStatement( temcourse);
            ps2 = con.prepareStatement( temporj );

            //ps.setString(2, password );
            //
            System.out.println(ps1.toString());
            name.setCellValueFactory(new PropertyValueFactory<Result, String>("name"));
            type.setCellValueFactory(new PropertyValueFactory<Result, String>("type"));
            results = new ArrayList<>();
            if(bothButton.isSelected()|| courseButton.isSelected()){
                rs = ps1.executeQuery();
                while (rs.next()) {
                    results.add(new Result(rs.getString("cname"),"Course"));
                    //
                    // resultView.getItems().add(new Result(rs.getString("cname"),"Course"));

                }
            }
            if(bothButton.isSelected()||projectButton.isSelected()){
                rs = ps2.executeQuery();
                while (rs.next()) {
                    results.add(new Result(rs.getString("pname"), "Project"));
                    //resultView.getItems().add(new Result(rs.getString("pname"), "Project"));
                }
            }

            result.getItems().setAll(results);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }

    }
    @FXML
    private void resetButtonPressed(){
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
            name.setCellValueFactory(new PropertyValueFactory<Result, String>("name"));
            type.setCellValueFactory(new PropertyValueFactory<Result, String>("type"));
            results = new ArrayList<>();
            rs = ps1.executeQuery();
            while (rs.next()) {
                results.add(new Result(rs.getString("cname"),"Course"));
                //
                // resultView.getItems().add(new Result(rs.getString("cname"),"Course"));

            }
            rs = ps2.executeQuery();
            while (rs.next()) {
                results.add(new Result(rs.getString("pname"), "Project"));
                //resultView.getItems().add(new Result(rs.getString("pname"), "Project"));
            }
            result.getItems().setAll(results);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }
    }
    @FXML
    private void resultPressed(){
        selectedResult = result.getSelectionModel().getSelectedItem();
        if (selectedResult.getType().equals("Project")){
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main/ViewApplyProject.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch (IOException e){

            }
        }
        else if (selectedResult.getType().equals("Course")){
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main/ViewCourse.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch (IOException e){

            }
        }
    }

}
