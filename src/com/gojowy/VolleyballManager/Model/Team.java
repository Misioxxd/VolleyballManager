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
    protected int place;

    public int getStrength() {
        return strength;
    }

    protected int strength;
    protected List<Player> playerList;

    public Team(String name, List playerList) {
        this.name = name;
        this.score = 0;
        this.win = 0;
        this.loose = 0;
        this.place = 1;
        this.playerList = playerList;
        this.strength = countStrength();
    }
    public void winner(){
        this.win++;
        this.score+=3;
    }
    public void looser(){
        this.loose++;
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

    public int getPlace() {
        return place;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
