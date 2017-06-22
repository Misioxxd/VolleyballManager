package com.gojowy.VolleyballManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by GM on 6/1/2017.
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/Start.fxml"));
        primaryStage.setTitle("Volleyball Manager");
        primaryStage.setScene(new Scene(root,500,500));
        primaryStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
