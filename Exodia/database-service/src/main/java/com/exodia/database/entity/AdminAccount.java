package com.exodia.database.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manlm1 on 9/11/2015.
 */
@Entity
@Table(name = "adminaccount")
public class AdminAccount implements Serializable {

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

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private UserRoles role;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private UserStatus status;

    @Column(name = "admin_creationTime")
    private long creationTime;

    @Column(name = "admin_lastUpdate")
    private long lastUpdate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "adminAccountAccessLog")
    @Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AdminAccessLog> adminAccessLogList = new ArrayList<>();

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

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
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

    public List<AdminAccessLog> getAdminAccessLogList() {
        return adminAccessLogList;
    }

    public void setAdminAccessLogList(List<AdminAccessLog> adminAccessLogList) {
        this.adminAccessLogList = adminAccessLogList;
    }
}
