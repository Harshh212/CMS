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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReviewlController implements Initializable {

    @FXML
    private TableColumn<reviewuser,String> Reviewlog;


    @FXML
    private TableView<reviewuser> table;

    @FXML
    private TableColumn<reviewuser,String> Username;

    @FXML
    private Button ReturnHome;

    private Scene scene;

    public ObservableList<reviewuser> Reviewloglist= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataBaseConnection connectNow=new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String complaintlistquery="SELECT user_details.user_Name,user_reviews.r_details FROM cms.user_details join cms.user_reviews where user_details.user_Id=user_reviews.u_Id;  ";
        try{
            Statement statement =connectDB.createStatement();
            ResultSet queryOutput= statement.executeQuery(complaintlistquery);

            while(queryOutput.next()){
                String queryuserid= queryOutput.getString("user_Name");
                String queryComplaint= queryOutput.getString("r_details");
                Reviewloglist.add(new reviewuser(queryuserid,queryComplaint));
            }
            Username.setCellValueFactory(new PropertyValueFactory<>("user_Name"));
            Reviewlog.setCellValueFactory(new PropertyValueFactory<>("r_details"));

            table.setItems(Reviewloglist);

        }catch(SQLException e){
            Logger.getLogger(complaintlogController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }
    public void DirectToHomePage(ActionEvent event) throws IOException {
        changeScenetoHome(event);
    }
    private void changeScenetoHome(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Temp.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException event) {
            throw new RuntimeException(String.valueOf(e));
        }
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Home");
    }


}
