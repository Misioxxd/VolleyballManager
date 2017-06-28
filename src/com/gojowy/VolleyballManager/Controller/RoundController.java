package com.gojowy.VolleyballManager.Controller;

import com.gojowy.VolleyballManager.Model.Match;
import com.gojowy.VolleyballManager.Model.Round;
import com.gojowy.VolleyballManager.Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by GM on 6/21/2017.
 */
public class RoundController implements Initializable {

    @FXML
    public Label matchLabel2;
    @FXML
    public Label matchLabel1;
    @FXML
    public Label matchLabel3;
    @FXML
    public Label matchLabel4;
    @FXML
    public Label matchLabel5;
    @FXML
    public Button backButton;
    @FXML
    public Button showResultButton;
    @FXML
    private TableView scoreboard;
    @FXML
    public TableColumn<Team, String> score;

    private List<Label> labelList = new ArrayList<>();
    protected int roundNumber ;
    protected List<Team> teamList;
    protected List<Match> matchList;


    public void setTeamList(List<Team> teamsList) {
        this.teamList = teamsList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Round round = new Round(this.teamList,roundNumber);
        round.start();
        this.matchList = round.getMatchList();
        this.assignLabelTolist();
        int i = 0;

        for (Match match : matchList) {
            setTextInlabels(match.showMatchTeams(), i);
            i++;
        }
        onClickBackButton();
        onClickShowResultsButton(round);

    }

    /**
     * Assing match to Labels
     */
    private void assignLabelTolist() {
        this.labelList.add(matchLabel1);
        this.labelList.add(matchLabel2);
        this.labelList.add(matchLabel3);
        this.labelList.add(matchLabel4);
        this.labelList.add(matchLabel5);
    }

    /**
     * Closing window after button click
     */
    public void onClickBackButton() {
        this.backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     * Showing results in labels after button click
     * @param round instance of round
     */
    public void onClickShowResultsButton(Round round) {
        this.showResultButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showResult();
                showResultButton.setDisable(true);
                backButton.setDisable(false);
                refreashTableView();
            }
        });
    }

    /**
     * Setting Text in labels
     * @param text
     * @param labelNumber
     */
    private void setTextInlabels(String text, int labelNumber) {
        this.labelList.get(labelNumber).setText(text);
    }

    /**
     * Looping on MatchList to show match results
     */
    private void showResult() {
        int i = 0;
        matchList.forEach(match -> match.proceed());
        for (Match match : matchList) {
            setTextInlabels(match.showTeamWithResult(), i);
            i++;
        }
    }


    /**
     * refresh view after round
     */
    private void refreashTableView() {
        this.scoreboard.refresh();
        sortScoreboard();
    }


    /**
     * Sort table after round
     */
    private void sortScoreboard() {
        score.setSortType(TableColumn.SortType.DESCENDING);
        this.scoreboard.getSortOrder().add(score);
        score.setSortable(true);
        scoreboard.sort();
    }
    public void setRoundNumber(int roundNumber)
    {
        this.roundNumber=roundNumber;
    }
    public void setViewTable(TableView scoreboard) {
        this.scoreboard = scoreboard;
    }
    public void setSortColumn(TableColumn<Team, String> score) {
        this.score = score;
    }
}
