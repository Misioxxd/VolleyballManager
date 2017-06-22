package com.gojowy.VolleyballManager.Controller;

import com.gojowy.VolleyballManager.Model.Round;
import com.gojowy.VolleyballManager.Model.Team;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by GM on 6/2/2017.
 */
public class MainController implements Initializable {

    @FXML
    public TableView<Team> scoreboard;
    @FXML
    public TableColumn<Team, String> teamName;
    @FXML
    public TableColumn<Team, String> teamWin;
    @FXML
    public TableColumn<Team, String> teamLoose;
    @FXML
    public TableColumn<Team, String> teamPlace;
    @FXML
    public TableColumn<Team, String> teamPoints;
    @FXML
    public Button nextRoundButton;


    private ObservableList<Team> teamsList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.teamsList = getTeamList();
        teamName.setCellValueFactory(new PropertyValueFactory<>("name"));
        teamWin.setCellValueFactory(new PropertyValueFactory<>("win"));
        teamLoose.setCellValueFactory(new PropertyValueFactory<>("loose"));
        teamPlace.setCellValueFactory(new PropertyValueFactory<>("place"));
        teamPoints.setCellValueFactory(new PropertyValueFactory<>("score"));
        this.scoreboard.setItems(this.teamsList);
        this.scoreboard.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        buttonActionOpenRoundWindow();
    }

    public void buttonActionOpenRoundWindow() {
        this.nextRoundButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene scene = null;
                try {
                    RoundController controller = new RoundController();
                    controller.setTeamList(teamsList);
                    Parent root = FXMLLoader.load(getClass().getResource("../View/Round.fxml"));

                    scene = new Scene(root, 600, 600);
                    Stage stage = new Stage();
                    stage.setTitle("Round");

                    stage.setScene(scene);

                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ObservableList<Team> getTeamList() {
        TeamController team = new TeamController();
        return team.getTeamList();
    }

    public ObservableList<Team> getTeams() {
        return teamsList;
    }
}
