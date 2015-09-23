package com.exodia.database.entity;

import javax.persistence.*;

/**
 * Created by manlm1 on 9/23/2015.
 */
@Entity
@Table(name = "playeraccount")
public class PlayerAccount {

    @Id
    @GeneratedValue
    @Column(name = "player_id")
    private int id;

    @Column(name = "player_email")
    private String email;

    @Column(name = "player_password")
    private String password;

    @Column(name = "player_status")
    private int status;

    @Column(name = "player_creationTime")
    private long creationTime;

    @Column(name = "player_lastUpdate")
    private long lastUpdate;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
}
