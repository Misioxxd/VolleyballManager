package com.gojowy.VolleyballManager.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.*;

/**
 * Created by GM on 6/2/2017.
 */
public class Round {
    protected int number;
    protected List<Team> beforeRoundTeamList;
    protected List<Match> matchList = new ArrayList<>();
    protected final int maxMatchNumber = 5;
    protected List<Team> afterRoundTeamList;


    public Round(List<Team> teamList, int number) {


        this.afterRoundTeamList = teamList;
        this.beforeRoundTeamList = FXCollections.observableArrayList(teamList);
        this.number = number;
    }


    public void start() {
        createMatchList();
    }

    private void createMatchList() {
        try {
            for (int i = 0; i < maxMatchNumber; i++) {
                Team first = getRandomTeam();
                Team second = getRandomTeam();
                this.matchList.add(new Match(first, second));

            }
            System.out.println("Match list created succesfull");
        } catch (Exception $e) {
            System.out.println($e.getMessage());
        }
    }

    public void setResultsAfter() {
        assignTeamsAfterMatch();
    }

    private void assignTeamsAfterMatch() {

        for (Match match : matchList) {
            for (Team team : afterRoundTeamList) {
                if (match.firstTeam.name.equals(team.name)) {
                    team = match.firstTeam;
                }

                if (match.secondTeam.name.equals(team.name)) {
                    team = match.secondTeam;
                }

            }
        }

    }

    private Team getRandomTeam() {
        Random random = new Random();
        int randomTeamIndex = random.nextInt(this.beforeRoundTeamList.size());
        Team randomTeam = this.beforeRoundTeamList.get(randomTeamIndex);
        this.beforeRoundTeamList.remove(randomTeamIndex);
        return randomTeam;
    }

    public List<Match> getMatchList() {
        return this.matchList;
    }
}

