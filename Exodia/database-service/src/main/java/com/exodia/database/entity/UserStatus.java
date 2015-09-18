package com.exodia.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by manlm1 on 9/14/2015.
 */
@Entity
@Table(name = "userstatus")
public class UserStatus {

    @Id
    @Column(name = "status_id")
    private int id;

    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
