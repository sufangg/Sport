package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sport_ID")
    private int sportId;

    @Column(name = "Sport_Name")
    private String sportName;

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
    private List<SportSession> sessions;

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
    private List<Modification> modifications;

    public Sport() {}

    public Sport(String sportName) {
        this.sportName = sportName;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public List<SportSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<SportSession> sessions) {
        this.sessions = sessions;
    }

    public List<Modification> getModifications() {
        return modifications;
    }

    public void setModifications(List<Modification> modifications) {
        this.modifications = modifications;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "sportId=" + sportId +
                ", sportName='" + sportName + '\'' +
                '}';
    }
}
