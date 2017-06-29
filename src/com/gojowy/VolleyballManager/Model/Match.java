package com.gojowy.VolleyballManager.Model;

import java.util.List;
import java.util.Random;

/**
 * Created by GM on 6/20/2017.
 */
public class Match {
    protected Team firstTeam;
    protected Team secondTeam;

    /**
     * Creating match
     *
     * @param firstTeam  random team when creating matchList
     * @param secondTeam random team when creating matchList
     */
    public Match(Team firstTeam, Team secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;

    }

    /**
     * Start match and setting data
     */
    public void proceed() {
        if (getWinner() == 1) {
            firstTeam.winner();
            secondTeam.looser();
        } else {
            firstTeam.looser();
            secondTeam.winner();
        }

    }

    /**
     * Return number of team who wins
     *
     * @return int
     */
    private int getWinner() {
        Random rand = new Random();
        int result = rand.nextInt(this.firstTeam.getStrength() + this.secondTeam.getStrength());
        if (result >= this.firstTeam.getStrength()) {
            return 1;
        } else
            return 2;

    }

    /**
     * Show team in match
     *
     * @return String
     */
    public String showMatchTeams() {
        return this.firstTeam.name + " vs " + this.secondTeam.name;
    }

    /**
     * Show match results
     *
     * @return String
     */
    private String showResults() {
        return this.firstTeam.getMatchScore() + ":" + this.secondTeam.getMatchScore();
    }

    /**
     * Show team and result
     *
     * @return String
     */
    public String showTeamWithResult() {
        String teamAndResult = showMatchTeams() + "\n" + showResults();
        return teamAndResult;
    }

    public Team getFirstTeam() {
        return this.firstTeam;
    }

    public Team getSecondTeam() {
        return this.secondTeam;
    }
}
