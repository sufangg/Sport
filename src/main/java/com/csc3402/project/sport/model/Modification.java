package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Modification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Modify_Date")
    private LocalDate modifyDate;

    @Column(name = "Modify_Time")
    private LocalTime modifyTime;

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private Sport sport;

    public Modification() {}

    public Modification(LocalDate modifyDate, LocalTime modifyTime, Admin admin, Sport sport) {
        this.modifyDate = modifyDate;
        this.modifyTime = modifyTime;
        this.admin = admin;
        this.sport = sport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }

    public LocalTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
