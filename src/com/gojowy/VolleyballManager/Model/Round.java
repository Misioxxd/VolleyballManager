package com.gojowy.VolleyballManager.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

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

    /**
     *
     * @param teamList list from ViewTable
     * @param roundNumber next round Number
     */
    public Round(List<Team> teamList, int roundNumber) {
        this.afterRoundTeamList = teamList;
        this.beforeRoundTeamList = FXCollections.observableArrayList(teamList);
        this.number = roundNumber;
    }

    /**
     * Starting round
     */
    public void start() {
        createMatchList();
    }

    /**
     * Creating match based on random Teams
     */
    private void createMatchList() {
        try {
            for (int i = 0; i < this.afterRoundTeamList.size()/2; i++) {
                Team first = getRandomTeam();
                Team second = getRandomTeam();
                this.matchList.add(new Match(first, second));
            }
            System.out.println("Match list created succesfull in round " + this.number);
        } catch (Exception $e) {
            System.out.println($e.getMessage());
        }
    }

    /**
     * Return random team from list
     * @return Team
     */
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

