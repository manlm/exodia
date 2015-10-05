package com.exodia.database.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by manlm1 on 10/2/2015.
 */
@Entity
@Table(name = "playerlog")
public class PlayerAccessLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "player_log_id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "player_id")
    private PlayerAccount playerAccount;

    @Column(name = "time")
    private long time;

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
