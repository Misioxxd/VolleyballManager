package com.gojowy.VolleyballManager.Controller;

import com.gojowy.VolleyballManager.Model.DataGather;
import com.gojowy.VolleyballManager.Model.Player;
import com.gojowy.VolleyballManager.Model.Team;
import javafx.collections.FXCollections;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    public TableColumn<Team, String> teamMatches;
    @FXML
    public TableColumn<Team, String> teamPoints;
    @FXML
    public Button nextRoundButton;

    private ObservableList<Team> teamsList = FXCollections.observableArrayList();
    private int maxRound;
    private int currentRound = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            generate();
            setTable();
            buttonActionOpenNewWindow();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open new window after button click after button click
     */
    public void buttonActionOpenNewWindow() {
        this.nextRoundButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isItEnd()) {
                    createRoundWindow();
                } else {
                    createWinnerWindow();
                }

            }
        });
    }

    /**
     * Open Round Window
     */
    private void createRoundWindow() {
        try {
            RoundController controller = new RoundController();
            setControllersValue(controller);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Round.fxml"));
            loader.setController(controller);
            Parent root = loader.load();//FXMLLoader.load(getClass().getResource("../View/Round.fxml"));
            root.autosize();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Round");
            stage.setScene(scene);
            stage.show();
            currentRound++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open Winner Window
     */
    private void createWinnerWindow() {
        try {
            WinnerController winnerController = new WinnerController();
            winnerController.setWinners(teamsList.get(0));
            Stage mainStage = (Stage) nextRoundButton.getScene().getWindow();
            mainStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Winner.fxml"));
            loader.setController(winnerController);
            Parent root = loader.load();//FXMLLoader.load(getClass().getResource("../View/Round.fxml"));
            root.autosize();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Winner");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setting values(teamList,scoreboard,teamPoints,currentRound) to controller
     *
     * @param controller RoundController
     */
    private void setControllersValue(RoundController controller) {
        controller.setTeamList(teamsList);
        controller.setViewTable(scoreboard);
        controller.setSortColumn(teamPoints);
        controller.setRoundNumber(currentRound);
    }

    /**
     * Setting teams into View Table
     */
    private void setTable() {
        teamName.setCellValueFactory(new PropertyValueFactory<>("name"));
        teamMatches.setCellValueFactory(new PropertyValueFactory<>("matches"));
        teamWin.setCellValueFactory(new PropertyValueFactory<>("win"));
        teamLoose.setCellValueFactory(new PropertyValueFactory<>("loose"));
        teamPoints.setCellValueFactory(new PropertyValueFactory<>("score"));
        this.scoreboard.setItems(this.teamsList);
        this.scoreboard.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * Gathering teams data from file
     *
     * @return List<String> teamsData
     */
    private List<String> getTeamDataFromFile() throws FileNotFoundException {
        DataGather dataFromFile = new DataGather("Teams.txt");
        dataFromFile.gatherData();
        return dataFromFile.getData();
    }

    /**
     * Gathering players data from file
     *
     * @return List<String> playersData
     */
    private List<String> getPlayerDataFromFile() throws FileNotFoundException {
        DataGather playersDataGather = new DataGather("Players.txt");
        playersDataGather.gatherData();
        return playersDataGather.getData();
    }

    /**
     * Generating teams and players lists
     */
    private void generate() throws FileNotFoundException {
        List<String> teamsFromFile = new ArrayList<>(getTeamDataFromFile());
        List<String> playersFromFile = new ArrayList<>(getPlayerDataFromFile());
        generateTeamList(teamsFromFile, playersFromFile);

    }

    /**
     * Returning list of 5 players to insert into Team
     *
     * @param playersData from file
     * @return List<Player>
     */
    private List<Player> getPlayersForTeam(List<String> playersData) {
        List<Player> playerList = new ArrayList<>();
        List<String> playerData = playersData;
        for (int i = 0; i < 5; i++) {
            playerList.add(new Player(playerData.get(i)));
            playerData.remove(i);
        }
        return playerList;
    }

    /**
     * Generating team list
     *
     * @param iteration
     * @param playersList
     * @param teamData    from file
     */
    private void generateTeam(int iteration, List<Player> playersList, List<String> teamData) {
        String name = teamData.get(0);
        teamData.remove(0);
        this.teamsList.add(new Team(name, playersList, iteration));
    }

    /**
     * Looping to generate teams
     *
     * @param teamData    from file
     * @param playersData from file
     */
    private void generateTeamList(List<String> teamData, List<String> playersData) {
        for (int i = 0; i < 10; i++) {
            generateTeam(i, getPlayersForTeam(playersData), teamData);

        }
    }

    /**
     * check if the maxRound is obtained
     *
     * @return boolean
     */
    private boolean isItEnd() {
        if (currentRound > maxRound) {
            return true;
        } else
            return false;
    }

    public void setMaxRound(int maxRound) {
        this.maxRound = maxRound;
    }

}
