package com.gojowy.VolleyballManager.Model;

import java.util.List;
import java.util.Random;

/**
 * Created by GM on 6/2/2017.
 */
public class Round {
    protected int number;
    protected List<Team> beforeRoundTeamList;
    protected List<Match> matchList;
    protected final int maxMatchNumber = 5;
    protected List<Team> afterRoundTeamList;

    public Round(List<Team> teamList, int number) {
        this.beforeRoundTeamList = teamList;
        this.number = number;
    }


    public void start() {
       createMatchList();
    }

    private void createMatchList()
    {
        for (int i = 0; i < maxMatchNumber; i++) {
            this.matchList.add(new Match(getRandomTeam(),getRandomTeam()));
        }
    }


    private Team getRandomTeam(){
        Random random = new Random();
        int randomTeamIndex = random.nextInt(this.beforeRoundTeamList.size()-1);
        Team randomTeam = this.beforeRoundTeamList.get(randomTeamIndex);
        this.beforeRoundTeamList.remove(randomTeamIndex);
        return randomTeam;
    }
    public List<Match> getMatchList()
    {
        return this.matchList;
    }
}

