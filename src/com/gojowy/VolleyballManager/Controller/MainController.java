package com.gojowy.VolleyballManager.Controller;

import com.gojowy.VolleyballManager.Model.Round;
import com.gojowy.VolleyballManager.Model.Team;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by GM on 6/2/2017.
 */
public class MainController implements Initializable {

    @FXML
    public AnchorPane stageMain;
    @FXML
    public TableView<Team> scoreboard;
    @FXML
    public TableColumn<Team, String> teamName;
    @FXML
    public TableColumn<Team, String> teamWin;
    @FXML
    public TableColumn<Team, String> teamLoose;
    @FXML
    public TableColumn<Team, String> teamMatches;
    @FXML
    public TableColumn<Team, String> teamPoints;
    @FXML
    public Button nextRoundButton;

    private void handleButtonAction(ActionEvent event) {

        System.out.println(stageMain.getScene());// Gives you the scene
    }

    private ObservableList<Team> teamsList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.teamsList = getTeamList();
        setTable();
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Round.fxml"));
                    loader.setController(controller);
                    Parent root = loader.load();//FXMLLoader.load(getClass().getResource("../View/Round.fxml"));
                    root.autosize();
                    Stage stage = new Stage();
                    scene = new Scene(root);
                    stage.setTitle("Round");
                    stage.setScene(scene);
                    stage.show();
                    scoreboard.refresh();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void setTable() {
        teamName.setCellValueFactory(new PropertyValueFactory<>("name"));
        teamMatches.setCellValueFactory(new PropertyValueFactory<>("matches"));
        teamWin.setCellValueFactory(new PropertyValueFactory<>("win"));
        teamLoose.setCellValueFactory(new PropertyValueFactory<>("loose"));

        teamPoints.setCellValueFactory(new PropertyValueFactory<>("score"));
        this.scoreboard.setItems(this.teamsList);
        this.scoreboard.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private ObservableList<Team> getTeamList() {
        TeamController team = new TeamController();
        return team.getTeamList();
    }

    public ObservableList<Team> getTeams() {
        return teamsList;
    }
}
