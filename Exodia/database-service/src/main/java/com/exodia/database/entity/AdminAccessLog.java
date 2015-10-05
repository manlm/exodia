package com.exodia.database.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by manlm1 on 10/2/2015.
 */
@Entity
@Table(name = "adminlog")
public class AdminAccessLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "admin_log_id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "admin_id")
    private AdminAccount adminAccountAccessLog;

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

    public AdminAccount getAdminAccountAccessLog() {
        return adminAccountAccessLog;
    }

    public void setAdminAccountAccessLog(AdminAccount adminAccountAccessLog) {
        this.adminAccountAccessLog = adminAccountAccessLog;
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
