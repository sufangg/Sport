package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Teacher_ID")
    private int teacherId;

    @Column(name = "Teacher_Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Room_Number")
    private String roomNumber;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Password")
    private String password;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<SportSession> sessions;

    public Teacher() {}

    public Teacher(String name, String email, String roomNumber, String phoneNumber, String password) {
        this.name = name;
        this.email = email;
        this.roomNumber = roomNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
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

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
