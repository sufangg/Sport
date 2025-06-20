package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @Column(name = "Teacher_ID", nullable = false)
    private String teacherId;

    @Column(name = "Teacher_Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Room_Number")
    private String roomNumber;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SportSession> sessions;

    // Constructors
    public Teacher() {
    }

    public Teacher(String teacherId, String name, String email, String roomNumber, String phoneNumber, String password) {
        this.teacherId = teacherId;
        this.name = name;
        this.email = email;
        this.roomNumber = roomNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    // Getters and Setters
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    public List<SportSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<SportSession> sessions) {
        this.sessions = sessions;
    }

    // toString
    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}