package com.exodia.database.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by manlm1 on 10/12/2015.
 */

@Entity
@Table(name = "highscore")
public class Highscore implements Serializable, Comparable<Highscore> {

    @Id
    @GeneratedValue
    @Column(name = "score_id")
    private int id;

    @OneToOne()
    @JoinColumn(name = "player_id")
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
    public int compareTo(Highscore o) {
        if (this.getScore() > o.getScore()) {
            return -1;
        }

        return 1;
    }
}
