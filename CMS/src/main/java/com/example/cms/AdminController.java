package com.example.cms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.UserPrincipal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController implements Initializable {

    @FXML
    private TableColumn<user,String> complaintlog;

    @FXML
    private TableView<user> table;

    @FXML
    private TableColumn<user,String> usernamelog;

    @FXML
    private TableColumn<user, Date> complaintdate;

    @FXML
    private Button BackToHome;

    @FXML
    private Button ViewAllComplaints;
    @FXML
    private TableColumn<reviewuser,String> Reviewlog;


    @FXML
    private TableView<reviewuser> table1;

    @FXML
    private TableColumn<reviewuser,String> Username;

    @FXML
    private Button ReturnHome;

    @FXML
    private Button BackToLogin;


    private Scene scene;


//    @FXML
//    private TableColumn<user,String > username;

    public ObservableList<user> complaintloglist= FXCollections.observableArrayList();
    public ObservableList<reviewuser> Reviewloglist= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataBaseConnection connectNow=new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String complaintlistquery="SELECT user_Id,c_details, c_Date FROM cms.user_complaints";
        String complaintlistquery1="SELECT user_details.user_Name,user_reviews.r_details FROM cms.user_details join cms.user_reviews where user_details.user_Id=user_reviews.u_Id;  ";
        try{
            Statement statement =connectDB.createStatement();
            ResultSet queryOutput= statement.executeQuery(complaintlistquery);

            while(queryOutput.next()){

                String queryuserID= queryOutput.getString("user_Id");
                String queryComplaint= queryOutput.getString("c_details");
                Date queryDate = queryOutput.getDate("c_Date");
                complaintloglist.add(new user(queryuserID,queryComplaint,queryDate));
            }
            usernamelog.setCellValueFactory(new PropertyValueFactory<>("user_Id"));
            complaintlog.setCellValueFactory(new PropertyValueFactory<>("c_details"));
            complaintdate.setCellValueFactory(new PropertyValueFactory<>("c_Date"));

            table.setItems(complaintloglist);

        }catch(SQLException e){
            Logger.getLogger(complaintlogController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }

        try{
            Statement statement =connectDB.createStatement();
            ResultSet queryOutput= statement.executeQuery(complaintlistquery1);

            while(queryOutput.next()){
                String queryuserid= queryOutput.getString("user_Name");
                String queryComplaint= queryOutput.getString("r_details");
                Reviewloglist.add(new reviewuser(queryuserid,queryComplaint));
            }
            Username.setCellValueFactory(new PropertyValueFactory<>("user_Name"));
            Reviewlog.setCellValueFactory(new PropertyValueFactory<>("r_details"));

            table1.setItems(Reviewloglist);

        }catch(SQLException e){
            Logger.getLogger(complaintlogController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }




    public void ViewAllComplaintsOnAction(ActionEvent event) throws IOException{
        DataBaseConnection connectNow=new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String complaintlistquery="SELECT user_Id,c_details, c_Date FROM cms.user_complaints";
        try{
            Statement statement =connectDB.createStatement();
            ResultSet queryOutput= statement.executeQuery(complaintlistquery);
            complaintloglist.clear();
            while(queryOutput.next()){

                String queryuserID= queryOutput.getString("user_Id");
                String queryComplaint= queryOutput.getString("c_details");
                Date queryDate = queryOutput.getDate("c_Date");
                complaintloglist.add(new user(queryuserID,queryComplaint,queryDate));
            }
            usernamelog.setCellValueFactory(new PropertyValueFactory<>("user_Id"));
            complaintlog.setCellValueFactory(new PropertyValueFactory<>("c_details"));
            complaintdate.setCellValueFactory(new PropertyValueFactory<>("c_Date"));

            table.setItems(complaintloglist);

        }catch(SQLException e){
            Logger.getLogger(complaintlogController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }

    public void DirectToLoginPage(ActionEvent event) throws IOException {
        changeScenetoHome(event);
    }
    private void changeScenetoHome(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException event) {
            throw new RuntimeException(String.valueOf(e));
        }
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Login");
    }


}
