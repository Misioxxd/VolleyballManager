package com.gojowy.VolleyballManager.Test;

import com.gojowy.VolleyballManager.Model.Match;
import com.gojowy.VolleyballManager.Model.Player;
import com.gojowy.VolleyballManager.Model.Team;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created by GM on 6/28/2017.
 */
public class MatchTest {


    @Test
    public void showMatchTeams() throws Exception {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("a b"));
        Team firstTeam = new Team("test1",playerList,1);
        Team secondTeam = new Team("test2",playerList,2);
        Match match = new Match(firstTeam,secondTeam);
        assert Objects.equals(match.showMatchTeams(),"test1 vs test2");
    }


}