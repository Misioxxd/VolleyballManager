package com.gojowy.VolleyballManager.Model;

import java.util.List;
import java.util.Random;

/**
 * Created by GM on 5/21/2017.
 */
public class Team {
    protected String name;
    protected final static int maxPlayers = 5;
    protected int score;
    protected int win;
    protected int loose;
    protected int id;
    protected int matchScore;
    protected int matches;

    public int getStrength() {
        return strength;
    }

    protected int strength;
    protected List<Player> playerList;

    public Team(String name, List playerList,int i) {
        this.name = name;
        this.score = 0;
        this.win = 0;
        this.loose = 0;
        this.id = i;
        this.matches=0;
        this.playerList = playerList;
        this.strength = countStrength();
    }
    public void winner(){
        this.win++;
        this.score+=3;
        this.matchScore = 3;
        this.matches++;
    }
    public void looser(){
        this.loose++;
        this.matchScore = getRandScore();
        this.matches++;
    }
    private int getRandScore(){
        Random rand = new Random();
        return rand.nextInt(3);
    }

    private int countStrength(){
        int strength = 0;
        for (Player player: this.playerList) {
            strength+=player.getScore();
        }
        return strength/playerList.size();
    }
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getWin() {
        return win;
    }

    public int getLoose() {
        return loose;
    }
    public int getMatches(){
        return matches;
    }
    public int getID() {
        return id;
    }

    public void setMatchScore(int matchScore){
        this.matchScore = matchScore;
    }
    public int getMatchScore(){
        return this.matchScore;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
