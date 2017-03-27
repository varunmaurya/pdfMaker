package com.varun.pdfmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("pdfmaker.fxml"));
        primaryStage.setTitle("PDFMaker");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }
}




