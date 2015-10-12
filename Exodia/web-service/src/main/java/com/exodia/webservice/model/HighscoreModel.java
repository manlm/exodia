package com.exodia.webservice.model;

import com.exodia.database.entity.Highscore;
import com.exodia.database.entity.PlayerScore;

import java.util.List;

/**
 * Created by manlm1 on 10/12/2015.
 */
public class HighscoreModel {

    private int rank;

    private int score;

    private List<PlayerHighScore> list;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<PlayerHighScore> getList() {
        return list;
    }

    public void setList(List<PlayerHighScore> list) {
        this.list = list;
    }
}
