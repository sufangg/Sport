package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Admin {
    @Id
    @Column(name = "Admin_ID")
    private String adminId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Password")
    private String password;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Modification> modifications;

    public Admin() {}

    public Admin(String adminId, String name, String password) {
        this.adminId = adminId;
        this.name = name;
        this.password = password;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Modification> getModifications() {
        return modifications;
    }

    public void setModifications(List<Modification> modifications) {
        this.modifications = modifications;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}