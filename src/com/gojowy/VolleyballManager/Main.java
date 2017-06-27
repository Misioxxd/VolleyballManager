package com.gojowy.VolleyballManager;

import com.gojowy.VolleyballManager.Controller.InputController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by GM on 6/1/2017.
 */
public class Main extends Application {


    @Override
    /**
     * Create new window
     */
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/UserInput.fxml"));
        root.autosize();
        primaryStage.setTitle("RoundNumbers");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    /**
     *  Run program with passed  arguments
     * @param args
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
