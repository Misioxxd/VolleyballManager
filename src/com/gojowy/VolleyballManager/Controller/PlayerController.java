package com.gojowy.VolleyballManager.Controller;

import com.gojowy.VolleyballManager.Model.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GM on 6/1/2017.
 */
public class PlayerController {

    protected List<String> playerData;
    protected FileController fileController;


    public PlayerController()
    {
        this.fileController = new FileController("Players.txt");
        this.playerData = fileController.getData();
    }

    public List getPlayersForTeam() {
        List<Player> playerList = new ArrayList<>();
       for (int i=0;i<5;i++)
       {
           playerList.add(new Player(playerData.get(i)));
            playerData.remove(i);
       }
       return playerList;
    }
}
