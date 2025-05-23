package com.csc3402.project.sport.model;

import jakarta.persistence.*;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Teacher_ID")
    private int tID;

    @Column(name = "Name")
    private String tName;

    @Column(name = "Email")
    private String tEmail;

    @Column(name = "Room_Number")
    private String tAddress;

    @Column(name = "Phone_Number")
    private String tPhone;

    @Column(name = "Password")
    private String tPassword;

    public Teacher(String tName, String tEmail, String tAddress, String tPhone, String tPassword) {
        this.tName = tName;
        this.tEmail = tEmail;
        this.tAddress = tAddress;
        this.tPhone = tPhone;
        this.tPassword = tPassword;
    }

    public Teacher() {
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

    public String gettEmail() {
        return tEmail;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public String gettAddress() {
        return tAddress;
    }

    public void settAddress(String tAddress) {
        this.tAddress = tAddress;
    }

    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone;
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }
}
