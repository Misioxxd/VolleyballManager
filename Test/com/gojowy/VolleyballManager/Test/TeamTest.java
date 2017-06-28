package com.gojowy.VolleyballManager.Test;

import com.gojowy.VolleyballManager.Model.Player;
import com.gojowy.VolleyballManager.Model.Team;
import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by GM on 6/28/2017.
 */
public class TeamTest {
    @Test
    public void setWinner(){
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("a b"));
        Team team = new Team("TeamName", playerList,1);
        team.winner();
        assert Objects.equals(team.getWin() ,1);
        assert Objects.equals(team.getScore() ,3);
        assert Objects.equals(team.getMatchScore() ,3);
        assert Objects.equals(team.getMatches() ,1);
    }
    @Test
    public void looser() throws Exception {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("a b"));
        Team team = new Team("TeamName", playerList,1);
        team.looser();
        assert  Objects.equals(team.getLoose(),1);
        assert Objects.equals(team.getMatches() ,1);
    }

}