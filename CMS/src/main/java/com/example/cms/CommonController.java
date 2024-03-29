package com.example.cms;

import javafx.application.Application;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;



public class CommonController implements Initializable {

    @FXML
    private Button backtohome;

    @FXML
    private Hyperlink numberlink;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private Scene scene;

    public void DirectToHomePage(ActionEvent event) throws IOException {
        changeScenetoHome(event);
    }

    private void changeScenetoHome(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Temp.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

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

