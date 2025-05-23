package com.csc3402.project.sport.model;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Admin_ID")
    private int tID;

    @Column(name = "Name")
    private String tName;

    @Column(name = "Password")
    private String tPassword;

    public Admin(String tName, String tPassword) {
        this.tName = tName;
        this.tPassword = tPassword;
    }

    public Admin() {
    }

    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }
}
