package com.csc3402.project.sport.model;

import jakarta.persistence.*;
@Entity
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "Student_ID")
    private int sID;

    @Column(name = "Name")
    private String sName;

    @Column(name = "Email")
    private String sEmail;

    @Column(name = "Address")
    private String sAddress;

    @Column(name = "Phone_Number")
    private String sPhone;

    @Column(name = "Password")
    private String sPassword;


    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }

    public Student(String sName, String sEmail, String sAddress, String sPhone, String sPassword) {
        this.sName = sName;
        this.sEmail = sEmail;
        this.sAddress = sAddress;
        this.sPhone = sPhone;
        this.sPassword = sPassword;
    }

    public Student() {
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }
}
