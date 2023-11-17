package com.example.cms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private static Stage stg;
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(scene);
//        stage.show();
//
//    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            stg = primaryStage;
            System.out.println("path:"+getClass().getResource("Login.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); //pass scene name here
            primaryStage.setScene(new Scene(root,600,400));
            primaryStage.show();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Login"); // set title of app

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    public void changeScene(String fxml) throws IOException{
//        System.out.println(fxml);
//        String fxml1 = "C:/Users/91829/OneDrive/Desktop/Intellij Projects/CMS/src/main/java/com/example/cms/" + fxml;
//        System.out.println(fxml1);
//        fxml1=fxml1.trim();
//        Parent pane= FXMLLoader.load(getClass().getResource(fxml1));
//        stg.getScene().setRoot(pane);
//    }

//    public void createAccountForm(){
//        try{
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Register.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//            Stage registerStage=new Stage();
//            stg=registerStage;
//            registerStage.initStyle(StageStyle.UNDECORATED);
//            registerStage.setScene(scene);
//            registerStage.show();
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }
//    }
//
    public static void main(String[] args) {
        launch();
    }
}