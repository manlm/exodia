package com.exodia.database.entity;

import javax.persistence.*;

/**
 * Created by manlm1 on 10/2/2015.
 */
@Entity
@Table(name = "adminlog")
public class AdminAccessLog {

    @Id
    @GeneratedValue
    @Column(name = "admin_log_id")
    private int id;

    @Column(name = "admin_id")
    private int adminId;

    @Column(name = "time")
    private long time;

    @Column(name = "action")
    private String action;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
