package com.example.cms;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import java.io.File;


public class HomeController implements Initializable {

    private Scene scene;

    @FXML
    private Button HomeDirectory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void DirectToLoginPage(ActionEvent event) throws IOException {
        changeScene(event);
    }
    private void changeScene(ActionEvent e) {
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
