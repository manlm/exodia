package com.exodia.database.entity;

/**
 * Created by manlm1 on 9/23/2015.
 */

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "playerscore")
public class PlayerScore implements Serializable, Comparable<PlayerScore> {

    @Id
    @GeneratedValue
    @Column(name = "score_id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "player_id")
    @JsonBackReference
    private PlayerAccount playerAccount;

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

    public PlayerAccount getPlayerAccount() {
        return playerAccount;
    }

    public void setPlayerAccount(PlayerAccount playerAccount) {
        this.playerAccount = playerAccount;
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

    @Override
    public int compareTo(PlayerScore o) {

        if (this.getPlayTime() > o.getPlayTime()) {
            return -1;
        }

        return 1;
    }
}
