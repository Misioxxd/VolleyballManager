package com.gojowy.VolleyballManager.Controller;

import com.gojowy.VolleyballManager.Model.DataGather;
import com.gojowy.VolleyballManager.Model.Player;
import com.gojowy.VolleyballManager.Model.Team;
import com.sun.scenario.effect.impl.prism.PrReflectionPeer;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by GM on 6/2/2017.
 */
public class MainController {


    private ObservableList<Team> teamsList = FXCollections.observableArrayList();
    private int maxRound;
    private int currentRound = 1;
    protected BufferedReader in;
    protected PrintWriter out;

    public MainController(int maxRound, BufferedReader in, PrintWriter out) {
        this.maxRound = maxRound;
        this.in = in;
        this.out = out;
    }

    /**
     * Start simulations
     *
     * @throws IOException
     */
    public void proceed() throws IOException {
        generate();
        out.println("Simulation started type help to show command list");
        boolean isNotEnd = true;
        while (isNotEnd) {
            switch (in.readLine().toUpperCase()) {
                case "SHOW SCOREBOARD": {
                    showScoreboard();
                    break;
                }
                case "QUIT": {
                    isNotEnd = false;
                    break;
                }
                case "NEXT ROUND": {
                    RoundController roundController = new RoundController(out, in, currentRound, teamsList);
                    roundController.proccess();
                    this.currentRound++;
                    break;
                }
                case "HELP": {
                    showHelp();
                    break;
                }
                default: {
                    out.println("Unknown command type help for command list");
                    break;
                }
            }

            isNotEnd = maxRoundAchieved();
        }
        if (!maxRoundAchieved()) {
            showEnd();
        }
    }

    /**
     * shows end message
     */
    private void showEnd() {
        showScoreboard();
        out.println("");
        out.println("");
        out.println("WINNER !!!!!!");
        out.println(teamsList.get(0).getName()+" with score "+teamsList.get(0).getScore());
    }
    /**
     * shows help
     */
    private void showHelp() {
        out.println("SHOW SCOREBOARD");
        out.println("NEXT ROUND");
        out.println("QUIT");
    }
    /**
     * shows scoreboard
     */
    private void showScoreboard() {
        out.println("name | matches | win | loose | score ");
        teamsList.forEach(team -> {
            out.println(team.getName() + " | " + team.getMatches() + " | " + team.getWin() + " | " + team.getLoose() + " | " + team.getScore());
        });
    }

    /**
     * check if max round is achieved
     * @return boolean
     */
    private boolean maxRoundAchieved() {
        if (currentRound > maxRound) {
            return false;
        } else
            return true;
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
