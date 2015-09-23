package com.exodia.database.entity;

/**
 * Created by manlm1 on 9/23/2015.
 */

import javax.persistence.*;

@Entity
@Table(name = "playerscore")
public class PlayerScore {

    @Id
    @GeneratedValue
    @Column(name = "score_id")
    private int id;

    @Column(name = "player_id")
    private int player_id;

    @Column(name = "score")
    private int score;

    @Column(name = "playTime")
    private long playTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
    }
}
