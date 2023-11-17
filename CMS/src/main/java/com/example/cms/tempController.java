package com.example.cms;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.sql.Date;


public class tempController implements Initializable {

    private Scene scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab HomeDirectory1;

    @FXML
    private Button submitimage;

    @FXML
    private TextArea tf_complaint;

    @FXML
    private TextArea tf_review;

    @FXML
    private Button r_button;;

    @FXML
    private Tab tab1;

    @FXML
    private Button uploadimagebutton;

    @FXML
    private Label WelcomeLabel;

    @FXML
    private Button LogOutButton;


    @FXML
    private Button contactus;

    @FXML
    private Label complaintlabel;

    @FXML
    private Label reviewlabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WelcomeLabel.setText("Welcome to CMS Portal! "+LoginController.user_name);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(20), WelcomeLabel);
        translateTransition.setFromX(0);
        translateTransition.setToX(150);
        translateTransition.play();
        translateTransition.setOnFinished(e -> WelcomeLabel.setText("Welcome to CMS Portal! "+LoginController.user_name));
        translateTransition.playFromStart();

    }
    public void DirectToLoginPagee(ActionEvent event) throws IOException {
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

    public void registerthecomplaint(ActionEvent event) throws IOException {
        try{if (!tf_complaint.getText().isBlank()) {
                    RegisterUsercomplaint();
                    playMusicOnSubmission();
                    changeScenetocomplaint(event);
                } else {
            playMusic();
            complaintlabel.setText("Complaint Section cannot be empty");
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), complaintlabel);
            fadeTransition.setFromValue(1.0); // Fully visible
            fadeTransition.setToValue(0.0); // Completely transparent
            fadeTransition.setOnFinished(e -> complaintlabel.setText(""));
            fadeTransition.play();
        }
          }catch (Exception ee){
            playMusic();
            complaintlabel.setText("Adding Image Is Mandatory");
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), complaintlabel);
            fadeTransition.setFromValue(1.0); // Fully visible
            fadeTransition.setToValue(0.0); // Completely transparent
            fadeTransition.setOnFinished(e -> complaintlabel.setText(""));
            fadeTransition.play();
        }
            }


    public void RegisterUsercomplaint() throws FileNotFoundException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDb = connectNow.getConnection();

        String Name = tf_complaint.getText();
        InputStream report = new FileInputStream(uploadimagebutton.getText());

//        Date currentDate = new Date();
//        Timestamp timestamp = new Timestamp(currentDate.getTime());
        Date currentDate = new Date(System.currentTimeMillis());
        try {
            String insertQuery = "INSERT INTO user_complaints (c_details, user_id, c_Image,c_Date) VALUES (?, ? , ?, ?)";
            PreparedStatement preparedStatement = connectDb.prepareStatement(insertQuery);


            preparedStatement.setString(1, Name);
            preparedStatement.setInt(2, LoginController.user_id);
            preparedStatement.setBlob(3, report);

            preparedStatement.setDate(4, currentDate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (report != null) {
                    report.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        public void UserReview(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDb = connectNow.getConnection();

        String Name = tf_review.getText();

        String insertFields =" Insert into user_reviews(r_details, u_id) values('";
        String insertValues=Name+"',"+LoginController.user_id+")";
        String insertToRegister=insertFields+insertValues;
        try {
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertToRegister);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void reviewOnAction(ActionEvent event) throws IOException {

            if (!tf_review.getText().isBlank()) {
                UserReview();
                playMusicOnSubmission();
                changeScenetoreview(event);
            }
        else {
                playMusic();
                reviewlabel.setText("Review Section cannot be empty");
                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), reviewlabel);
                fadeTransition.setFromValue(1.0);
                fadeTransition.setToValue(0.0);
                fadeTransition.setOnFinished(e -> reviewlabel.setText(""));
                fadeTransition.play();
            }

    }

    private void changeScenetoreview(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Reviewl.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException event) {
            throw new RuntimeException(String.valueOf(e));
        }
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Review");
    }

    private void changeScenetocomplaint(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Complaintl.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException event) {
            throw new RuntimeException(String.valueOf(e));
        }
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Complaint");
    }

    private void changeSceneTocomplaintslog(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("complaintlog.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException event) {
            throw new RuntimeException(String.valueOf(e));
        }
        stage.setScene(scene);
        stage.show();
        stage.setTitle("ComplaintLog");
    }

    public void DirectToComplainLogPage(ActionEvent event) throws IOException {
        changeSceneTocomplaintslog(event);
    }

    private void changeSceneTocomplaintslog1(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Reviewlog.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException event) {
            throw new RuntimeException(String.valueOf(e));
        }
        stage.setScene(scene);
        stage.show();
        stage.setTitle("ReviewLog");
    }

    public void DirectToComplainLogPage1(ActionEvent event) throws IOException {
        changeSceneTocomplaintslog1(event);
    }

    @FXML
    void uploadSetOnAction(ActionEvent event) {
       try{ FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(uploadimagebutton.getScene().getWindow());
        String filename = file.getAbsolutePath();
        uploadimagebutton.setText(filename);
       }catch (NullPointerException ignored){
           System.out.println("Image Not selected");
       }




    }

    private void playMusicOnSubmission() {
        String audioFile = "C:/Users/91829/OneDrive/Desktop/Java Music/ComplaintRegistered.mp3"; // Replace with the correct path
        Media media = new Media(new File(audioFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void DirectTocustomerSupport(ActionEvent event) throws IOException {
        changeSceneToCustomerSupport(event);
    }

    private void changeSceneToCustomerSupport(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Customer_Care.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException event) {
            throw new RuntimeException(String.valueOf(e));
        }
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Customer Support");
    }

    private void playMusic() {
        String audioFile = "C:/Users/91829/OneDrive/Desktop/Java Music/erro.mp3";
        Media media = new Media(new File(audioFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

}
