package com.gojowy.VolleyballManager.Controller;

import com.gojowy.VolleyballManager.Model.Player;
import com.gojowy.VolleyballManager.Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GM on 6/1/2017.
 */
public class TeamController {
    protected List<String> teamData;
    protected FileController fileController;
    protected ObservableList<Team> teamList =  FXCollections.observableArrayList();;
    protected PlayerController playerController;

    public ObservableList<Team> getTeamList() {
        return teamList;
    }

    public TeamController()
    {
        this.fileController = new FileController("Teams.txt");
        this.teamData = fileController.getData();
        this.playerController = new PlayerController();
        generateTeamList();
    }

    private void generateTeam(int i)
    {
        String name = this.teamData.get(0);
        this.teamData.remove(0);
        this.teamList.add(new Team(name,playerController.getPlayersForTeam(),i));
    }
    private void generateTeamList()
    {
        for (int i=0;i<10;i++)
        {
            generateTeam(i);

        }
    }


}
