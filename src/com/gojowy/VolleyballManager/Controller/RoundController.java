package com.gojowy.VolleyballManager.Controller;

import com.gojowy.VolleyballManager.Model.Match;
import com.gojowy.VolleyballManager.Model.Round;
import com.gojowy.VolleyballManager.Model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by GM on 6/21/2017.
 */
public class RoundController {


    protected int roundNumber;
    protected List<Team> teamList;
    protected List<Match> matchList;
    protected BufferedReader in;
    protected PrintWriter out;


    public RoundController(PrintWriter out, BufferedReader in, int roundNumber, List<Team> teams) {
        this.out = out;
        this.in = in;
        this.roundNumber = roundNumber;
        this.teamList = teams;
    }

    /**
     *  Start Rounds
     * @throws IOException
     */
    public void proccess() throws IOException {
        Round round = new Round(this.teamList, roundNumber);
        round.start();
        out.println("Start new round "+this.roundNumber+" type help for more commands");
        boolean isItNotEnd = true;
        while (isItNotEnd) {
            switch (in.readLine().toUpperCase()) {
                case "SHOW RESULTS": {
                    round.getMatchList().forEach(match -> out.println(match.showTeamWithResult()));
                    isItNotEnd = false;
                    out.println("Round ends type help for more commands");
                    break;
                }
                case "HELP": {
                    showHelp();
                    break;
                } default: {
                    out.println("Unknown command type help for command list");
                    break;
                }
            }
            sortScoreboard();
        }
    }

    /**
     * shows help
     */
    private void showHelp() {
        out.println("SHOW RESULTS");
    }


    /**
     * Sort table after round
     */
    private void sortScoreboard() {
        Collections.sort(teamList, (left, right) ->  right.getScore() - left.getScore() );
    }


}
