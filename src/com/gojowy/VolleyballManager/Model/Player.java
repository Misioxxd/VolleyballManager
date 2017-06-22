package com.gojowy.VolleyballManager.Model;

import java.util.Random;

/**
 * Created by GM on 5/21/2017.
 */
public class Player {
    protected String name;
    protected String surname;
    protected int score;

    public Player(String data) {
        this.name = getNameFromData(splitData(data));
        this.surname = getSurnameFromData(splitData(data));
        this.score = getRandomScore();
    }

    private int getRandomScore() {
        Random rand = new Random();
        return rand.nextInt(40) + 50;
    }
    private String[] splitData(String data)
    {
        return data.split(" ");
    }
    private String getNameFromData(String[] data)
    {
     return data[0];
    }
    private String getSurnameFromData(String[] data)
    {
        return data[1];
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    public int getScore(){return score;}
}