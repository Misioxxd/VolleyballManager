package com.gojowy.VolleyballManager.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by GM on 6/27/2017.
 */
public class InputController implements Initializable {
    @FXML
    public TextField roundNumber;
    @FXML
    public Button startButton;
    @FXML
    public Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonActionOpenRoundWindow();
    }

    /**
     * Creating new window after button click
     */
    public void buttonActionOpenRoundWindow() {
        this.startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene scene = null;
                try {
                    MainController controller = new MainController();
                    if (canGo(controller)) {
                        Stage firstStage = (Stage) startButton.getScene().getWindow();
                        firstStage.close();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Start.fxml"));
                        loader.setController(controller);
                        Parent root = loader.load();
                        root.autosize();
                        Stage stage = new Stage();
                        scene = new Scene(root);
                        stage.setTitle("Main");
                        stage.setScene(scene);
                        stage.show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Validate data passed by user
     * @return boolean
     */
    private boolean isNumberValid() {
        String value = this.roundNumber.getText();
        if (value.matches("\\D{1,}") || value.equals("")) {
            return false;
        } else if (Integer.parseInt(value) > 5) {
            return true;
        } else
            return false;
    }

    /**
     * Control behaviour after data validation
     * @param controller
     * @return
     */
    private boolean canGo(MainController controller) {
        if (isNumberValid()) {
            controller.setMaxRound(Integer.parseInt(this.roundNumber.getText()));
            return true;
        } else {
            this.roundNumber.setText("");
            this.errorLabel.setText("Insert right amound of Round \n(more than 5)");
            return false;
        }

    }
}