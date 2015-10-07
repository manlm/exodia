package com.exodia.database.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manlm1 on 9/23/2015.
 */
@Entity
@Table(name = "playeraccount")
public class PlayerAccount implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "player_id")
    private int id;

    @Column(name = "player_email")
    private String email;

    @Column(name = "player_password")
    private String password;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private UserStatus status;

    @Column(name = "player_creationTime")
    private long creationTime;

    @Column(name = "player_lastUpdate")
    private long lastUpdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playerAccount")
    @Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PlayerAccessLog> playerAccessLogList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playerAccount")
    @Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PlayerScore> playerScoreList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<PlayerAccessLog> getPlayerAccessLogList() {
        return playerAccessLogList;
    }

    public void setPlayerAccessLogList(List<PlayerAccessLog> playerAccessLogList) {
        this.playerAccessLogList = playerAccessLogList;
    }

    public List<PlayerScore> getPlayerScoreList() {
        return playerScoreList;
    }

    public void setPlayerScoreList(List<PlayerScore> playerScoreList) {
        this.playerScoreList = playerScoreList;
    }
}
