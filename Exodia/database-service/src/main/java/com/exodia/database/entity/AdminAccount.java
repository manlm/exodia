package com.exodia.database.entity;

import javax.persistence.*;

/**
 * Created by manlm1 on 9/11/2015.
 */
@Entity
@Table(name = "adminaccount")
public class AdminAccount {

    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    private int id;

    @Column(name = "admin_username")
    private String username;

    @Column(name = "admin_password")
    private String password;

    @Column(name = "admin_email")
    private String email;

    @Column(name = "role")
    private int role;

    @Column(name = "admin_status")
    private int status;

    @Column(name = "admin_creationTime")
    private long creationTime;

    @Column(name = "admin_lastUpdate")
    private long lastUpdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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
