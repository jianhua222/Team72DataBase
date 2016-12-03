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
            List<Result> tem = new ArrayList<>();
            rs = ps1.executeQuery();
            while (rs.next()) {
                tem.add(new Result(rs.getString("cname"),"Course"));
                //
                // resultView.getItems().add(new Result(rs.getString("cname"),"Course"));

            }
            rs = ps2.executeQuery();
            while (rs.next()) {
                tem.add(new Result(rs.getString("pname"), "Project"));
                //resultView.getItems().add(new Result(rs.getString("pname"), "Project"));
            }
            result.getItems().setAll(tem);
        } catch(Exception e) {
            System.err.println("Exception: 11" + e.getMessage());

        }
    }
    @FXML
    private void selectCategoryPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main/CategorySelect.fxml"));
            Stage primaryStage = (Stage) selectCategory.getScene().getWindow();
            primaryStage.setTitle("MainPage");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
        catch (IOException e){

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
        String designation = designationBox.getValue().toString();
        String year = yearBox.getValue().toString();
        String major = majorBox.getValue().toString();
        ArrayList<String> categories = CategorySelectController.selectedCategories;
    }
    @FXML
    private void resetButtonPressed(){

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
    }

}
