package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Sport {
    @Id
    @Column(name = "Sport_ID")
    private String sportId;

    @Column(name = "Sport_Name")
    private String sportName;

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
    private List<SportSession> sessions;

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
    private List<Modification> modifications;

    public Sport() {}

    public Sport(String sportId, String sportName) {
        this.sportId = sportId;
        this.sportName = sportName;
    }

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
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
