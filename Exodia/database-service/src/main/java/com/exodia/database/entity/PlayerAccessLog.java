package com.exodia.database.entity;

import javax.persistence.*;

/**
 * Created by manlm1 on 10/2/2015.
 */
@Entity
@Table(name = "playerlog")
public class PlayerAccessLog {

    @Id
    @GeneratedValue
    @Column(name = "player_log_id")
    private int id;

    @Column(name = "player_id")
    private int playerId;

    @Column(name = "time")
    private long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
