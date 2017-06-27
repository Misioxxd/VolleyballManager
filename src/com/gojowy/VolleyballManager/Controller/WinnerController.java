package com.gojowy.VolleyballManager.Controller;

import com.gojowy.VolleyballManager.Model.Team;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;


/**
 * Created by GM on 6/27/2017.
 *
 */
public class WinnerController implements Initializable {
    @FXML
    public Label winnerTeam;

    protected  Team winners;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.winnerTeam.setText(this.winners.getName());
    }

    public void setWinners(Team winners) {
        this.winners = winners;
    }

}
