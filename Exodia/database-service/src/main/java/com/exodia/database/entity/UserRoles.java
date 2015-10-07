package com.exodia.database.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manlm1 on 9/11/2015.
 */
@Entity
@Table(name = "userroles")
public class UserRoles implements Serializable {

    @Id
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    @Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AdminAccount> adminAccountList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<AdminAccount> getAdminAccountList() {
        return adminAccountList;
    }

    public void setAdminAccountList(List<AdminAccount> adminAccountList) {
        this.adminAccountList = adminAccountList;
    }
}
