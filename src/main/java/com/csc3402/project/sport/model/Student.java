package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    @Column(name = "Student_ID")
    private String studentId;

    @Column(name = "Student_Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Password")
    private String password;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    public Student() {}

    public Student(String studentId, String name, String email, String address, String phoneNumber, String password) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}