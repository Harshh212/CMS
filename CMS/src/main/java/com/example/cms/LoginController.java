package com.example.cms;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.util.Duration;

import static javafx.application.Application.launch;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;

public class LoginController implements Initializable {
    public static int user_id;
    public static String user_name;

//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
    @FXML
    private Button button_login;

    @FXML
    private ImageView ProfileImageView;

    @FXML
    private ImageView KeyImageView;

    @FXML
    private ImageView LogoImageView;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Label LoginMessageLabel;

    @FXML
    private Hyperlink signup_link;

    @FXML
    private Button directtocomplaintslog;



    //    private Stage stage;
    private Scene scene;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        Image ProfileImage = new Image("file:profile.png");
//        ProfileImageView.setImage(ProfileImage);

//        Image image= new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/IMAGES/key.png"))));
//        KeyImageView.setImage(image);

//        File KeyFile = new File("key.png");
//        Image KeyImage = new Image(KeyFile.toURI().toString());
//        KeyImageView.setImage(KeyImage);
//
//        File LogoFile = new File("user.gif");
//        Image LogoImage = new Image(LogoFile.toURI().toString());
//        LogoImageView.setImage(LogoImage);
    }
    public void loginButtonOnAction(ActionEvent event){
        System.out.println("inside loginbuttonclick");

        if(tf_username.getText().isBlank () == false && tf_password.getText().isBlank() == false){
           validatelogin(event);
        }
        else{
            playMusic();
            LoginMessageLabel.setText("Please Enter username and password");
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), LoginMessageLabel);
            fadeTransition.setFromValue(1.0); // Fully visible
            fadeTransition.setToValue(0); // Completely transparent
            fadeTransition.setOnFinished(e -> LoginMessageLabel.setText(""));
            fadeTransition.play();

//            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), LoginMessageLabel);
//            translateTransition.setFromX(0);
//            translateTransition.setToX(20);
//            translateTransition.setOnFinished(e -> LoginMessageLabel.setText(""));
//            translateTransition.play();
        }
    }

    public void sSignup_linkOnAction(ActionEvent event) throws IOException {
//        FXMLLoader signup = new FXMLLoader(Main.class.getResource("Register.fxml"));
//        Scene scene = new Scene(signup.load(), 600, 400);
//        Stage registerStage=new Stage();
//        registerStage.initStyle(StageStyle.UNDECORATED);
//        registerStage.setScene(scene);
//        registerStage.show();
        changeSceneToSignUp(event);
    }

    public void validatelogin(ActionEvent event){
        System.out.println("inside validate");
        DataBaseConnection connectNow=new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        Main n=new Main();

        String verifyLogin = "select * from cms.user_details where user_Username = '" + tf_username.getText() +"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (queryResult.next()) {
                if (queryResult.getString("user_Password").compareTo(tf_password.getText()) == 0) {
                    user_id = queryResult.getInt("user_Id");
                    user_name = queryResult.getNString("user_Username");
                    String userRole = queryResult.getString("user_Role");
                    if ("admin".equals(userRole)) {
                        // Redirect to the admin dashboard
                        playMusicOnLogin();
                        changeSceneToAdminDashboard(event);
                    } else {
                        // Redirect to the home page
                        LoginMessageLabel.setText("      Logged In Successfully!   ");
                        playMusicOnLogin();
                        changeSceneToHome(event);
                    }

//                    LoginMessageLabel.setText("      Logged In Successfully!   ");
//                    playMusicOnLogin();
//                    changeSceneToHome(event);
                } else {
                    playMusic();
                    LoginMessageLabel.setText("Invalid credentials. Please try again.");
                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), LoginMessageLabel);
                    fadeTransition.setFromValue(1.0); // Fully visible
                    fadeTransition.setToValue(0.0); // Completely transparent
                    fadeTransition.setOnFinished(e -> LoginMessageLabel.setText(""));
                    fadeTransition.play();
                }
            } else {
                playMusic();
                LoginMessageLabel.setText("Invalid credentials. Please try again.");
                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), LoginMessageLabel);
                fadeTransition.setFromValue(1.0); // Fully visible
                fadeTransition.setToValue(0.0); // Completely transparent
                fadeTransition.setOnFinished(e -> LoginMessageLabel.setText(""));
                fadeTransition.play();
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

//    public void createAccountForm(){
//        try{
//
//            FXMLLoader signup = new FXMLLoader(Main.class.getResource("Register.fxml"));
//            System.out.println("Inside Switchingscene");
//            Scene scene = new Scene(signup.load(), 600, 400);
//            Stage registerStage=new Stage();
//            registerStage.initStyle(StageStyle.UNDECORATED);
//            registerStage.setScene(scene);
//            registerStage.setTitle("Registration Form");
//            registerStage.show();
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }
private void changeSceneToSignUp(ActionEvent e) {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Register.fxml"));
    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();

    try {
        scene = new Scene(fxmlLoader.load());
    } catch (IOException event) {
        throw new RuntimeException(String.valueOf(e));
    }
    stage.setScene(scene);
    stage.show();
    stage.setTitle("Register");
}

    private void changeSceneToHome(ActionEvent e) {
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

    private void changeSceneToAdminDashboard(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminDashboard.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException event) {
            throw new RuntimeException(String.valueOf(e));
        }
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Admin Dashboard");
    }

    private void playMusic() {
        String audioFile = "C:/Users/91829/OneDrive/Desktop/Java Music/erro.mp3";
        Media media = new Media(new File(audioFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private void playMusicOnLogin() {
        String audioFile = "C:/Users/91829/OneDrive/Desktop/Java Music/LoginSound.mp3";
        Media media = new Media(new File(audioFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    }
