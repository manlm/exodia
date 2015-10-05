package com.exodia.database.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manlm1 on 9/14/2015.
 */
@Entity
@Table(name = "userstatus")
public class UserStatus implements Serializable {

    @Id
    @Column(name = "status_id")
    private int id;

    @Column(name = "status")
    private String status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "status")
    @Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AdminAccount> adminAccountList = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "status")
    @Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PlayerAccount> playerAccountList = new ArrayList<>();

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

    public List<AdminAccount> getAdminAccountList() {
        return adminAccountList;
    }

    public void setAdminAccountList(List<AdminAccount> adminAccountList) {
        this.adminAccountList = adminAccountList;
    }

    public List<PlayerAccount> getPlayerAccountList() {
        return playerAccountList;
    }

    public void setPlayerAccountList(List<PlayerAccount> playerAccountList) {
        this.playerAccountList = playerAccountList;
    }
}
