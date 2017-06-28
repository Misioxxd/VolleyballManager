package com.gojowy.VolleyballManager.Test;

import com.gojowy.VolleyballManager.Model.DataGather;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by GM on 6/28/2017.
 */
public class DataGatherTest {
    @Test
    public void gatherPlayerData() throws Exception {
        DataGather dataGather = new DataGather("Players.txt");
        dataGather.gatherData();
        assertEquals(dataGather.getData().get(1),"Grzegorz Pająk");
        assertEquals(dataGather.getData().get(dataGather.getData().size()-1),"Marcin Kryś");
        assertEquals(dataGather.getData().size(),58);
    }
    @Test
    public void gatherTeamData() throws Exception {
        DataGather dataGather = new DataGather("Teams.txt");
        dataGather.gatherData();
        assertEquals(dataGather.getData().get(1),"Asseco Resovia Rzeszów");
        assertEquals(dataGather.getData().get(dataGather.getData().size()-1),"GKS Katowice");
        assertEquals(dataGather.getData().size(),10);
    }


}