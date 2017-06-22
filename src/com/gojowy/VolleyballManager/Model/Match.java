package com.gojowy.VolleyballManager.Model;

import java.util.List;
import java.util.Random;

/**
 * Created by GM on 6/20/2017.
 */
public class Match {
    protected Team firstTeam ;
    protected Team secondTeam;

    public Match(Team firstTeam, Team secondTeam){
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public void proceed(){
        if(getWinner()==1)
        {
            firstTeam.winner();
            secondTeam.looser();
        }
        else
        {
            firstTeam.looser();
            secondTeam.winner();
        }

    }

    private int getWinner()
    {
        Random rand = new Random();
        int result = rand.nextInt(this.firstTeam.getStrength()+this.secondTeam.getStrength());
        if(result>=this.firstTeam.getStrength())
        {
            return 1;
        }
        else
            return 2;

    }
    public String showMatchTeams()
    {
        return this.firstTeam.name+" vs "+this.secondTeam.name;
    }
    public Team getFirstTeam(){
        return this.firstTeam;
    }
    public Team getSecondTeam(){
        return this.secondTeam;
    }
}
