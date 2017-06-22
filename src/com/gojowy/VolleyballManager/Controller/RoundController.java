package com.gojowy.VolleyballManager.Controller;

import com.gojowy.VolleyballManager.Main;
import com.gojowy.VolleyballManager.Model.Match;
import com.gojowy.VolleyballManager.Model.Round;
import com.gojowy.VolleyballManager.Model.Team;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
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

    private List<Label> labelList = new ArrayList<>();
    protected Round round ;
    protected List<Team> teamList;
    protected List<Match> matchList;



    public void setTeamList(List<Team> teamsList){
        this.teamList = teamsList;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Round round = new Round(this.teamList,1);
        round.start();
        this.matchList = round.getMatchList();
        this.assignLabelTolist();
        int i=0;
        for (Match match: matchList) {
           this.labelList.get(i).setText(match.showMatchTeams());
            i++;
        }

    }
    private void assignLabelTolist()
    {
        this.labelList.add(matchLabel1);
        this.labelList.add(matchLabel2);
        this.labelList.add(matchLabel3);
        this.labelList.add(matchLabel4);
        this.labelList.add(matchLabel5);
    }
}
