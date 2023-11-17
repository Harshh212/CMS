package com.example.cms;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private Hyperlink LogInLink;

    @FXML
    private Button button_signup;

    @FXML
    private ImageView signupimageview;

    @FXML
    private PasswordField tf_confirmpassword;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_name;

    @FXML
    private PasswordField tf_password;

    @FXML
    private TextField tf_username;

    @FXML
    private Label credentialmatchlabel;


    private Scene scene;


    public void initialize(URL location, ResourceBundle resources) {
//        File SignFile = new File("IMAGES/SignUpImage.jpg");
//        Image SignUpImage = new Image(SignFile.toURI().toString());
//        signupimageview.setImage(SignUpImage);
    }

    public void LogInLinkOnAction(ActionEvent event) throws IOException {
//        FXMLLoader signup = new FXMLLoader(Main.class.getResource("Login.fxml"));
//        Scene scene = new Scene(signup.load(), 600, 400);
//        Stage registerStage=new Stage();
//        registerStage.initStyle(StageStyle.UNDECORATED);
//        registerStage.setScene(scene);
//        registerStage.show();

        changeSceneToLogin(event);
    }

    public void changescenetoHome(ActionEvent event) throws IOException {
        if (!tf_password.getText().isBlank()) {
            if ((!tf_username.getText().isBlank()) && (!tf_confirmpassword.getText().isBlank()) && (!tf_email.getText().isBlank()) && (!tf_name.getText().isBlank())) {
                if (Objects.equals(tf_password.getText(), tf_confirmpassword.getText())) {
                    RegisterUser(event);
                } else {
                    playMusic();
                    credentialmatchlabel.setText("Password's Mismatched");
                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), credentialmatchlabel);
                    fadeTransition.setFromValue(1.0); // Fully visible
                    fadeTransition.setToValue(0.0); // Completely transparent
                    fadeTransition.setOnFinished(e -> credentialmatchlabel.setText(""));
                    fadeTransition.play();
                }
            } else {
                playMusic();
                credentialmatchlabel.setText("Please Enter Valid Credentials");
                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), credentialmatchlabel);
                fadeTransition.setFromValue(1.0); // Fully visible
                fadeTransition.setToValue(0.0); // Completely transparent
                fadeTransition.setOnFinished(e -> credentialmatchlabel.setText(""));
                fadeTransition.play();
            }
        } else {
            playMusic();
            credentialmatchlabel.setText("Please Enter Valid Credentials");
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), credentialmatchlabel);
            fadeTransition.setFromValue(1.0); // Fully visible
            fadeTransition.setToValue(0.0); // Completely transparent
            fadeTransition.setOnFinished(e -> credentialmatchlabel.setText(""));
            fadeTransition.play();
        }

    }

    private void changeSceneToLogin(ActionEvent e) {
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

//    public void RegisterUser(){
//        DataBaseConnection connectNow = new DataBaseConnection();
//        Connection connectDb = connectNow.getConnection();
//
//        String Name = tf_name.getText();
//        String Email = tf_email.getText();
//        String UserName = tf_username.getText();
//        String Password = tf_password.getText();
////        String ConfirmPassword = tf_confirmpassword.getText();
//
//        String insertFields =" Insert into user_details(user_Name,user_Email,user_Username,user_Password) values('";
//        String insertValues=Name+"','"+Email+"','"+UserName+"','"+Password+"')";
//        String insertToRegister=insertFields+insertValues;
//        try {
//            Statement statement = connectDb.createStatement();
//            statement.executeUpdate(insertToRegister);
//        }catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }
//    }

    public void RegisterUser(ActionEvent event) {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDb = connectNow.getConnection();

        String Name = tf_name.getText();
        String Email = tf_email.getText();
        String UserName = tf_username.getText();
        String Password = tf_password.getText();

        // Check if the username already exists
        if (isUsernameExists(UserName)) {
            playMusic();
            credentialmatchlabel.setText("Username already exists.");
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), credentialmatchlabel);
            fadeTransition.setFromValue(1.0); // Fully visible
            fadeTransition.setToValue(0.0); // Completely transparent
            fadeTransition.setOnFinished(e -> credentialmatchlabel.setText(""));
            fadeTransition.play();
        } else if (isEmailExists(Email)) {
            playMusic();
            credentialmatchlabel.setText("Email already exists.");
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), credentialmatchlabel);
            fadeTransition.setFromValue(1.0); // Fully visible
            fadeTransition.setToValue(0.0); // Completely transparent
            fadeTransition.setOnFinished(e -> credentialmatchlabel.setText(""));
            fadeTransition.play();
        } else {
            String insertFields = "INSERT INTO user_details (user_Name, user_Email, user_Username, user_Password) VALUES ('";
            String insertValues = Name + "','" + Email + "','" + UserName + "','" + Password + "')";
            String insertToRegister = insertFields + insertValues;
            changeScene(event);
            //playMusicOnRegister();

            try {
                Statement statement = connectDb.createStatement();
                statement.executeUpdate(insertToRegister);
                changescenetoHome(event);
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    // Function to check if the username already exists
    private boolean isUsernameExists(String username) {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDb = connectNow.getConnection();

        String checkUsername = "SELECT user_Username FROM user_details WHERE user_Username = '" + username + "'";

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(checkUsername);
            return queryResult.next(); // Return true if a row is found (username already exists)
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Assume an error means the username doesn't exist
        }
    }

    private boolean isEmailExists(String Email) {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDb = connectNow.getConnection();

        String checkUsername = "SELECT user_Email FROM user_details WHERE user_Email = '" + Email + "'";

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(checkUsername);
            return queryResult.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void playMusic() {
        String audioFile = "C:/Users/91829/OneDrive/Desktop/Java Music/erro.mp3";
        Media media = new Media(new File(audioFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private void playMusicOnRegister() {
        String audioFile = "C:/Users/91829/OneDrive/Desktop/Java Music/LoginSound.mp3";
        Media media = new Media(new File(audioFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }


}
