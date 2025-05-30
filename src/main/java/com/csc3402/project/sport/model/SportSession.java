package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class SportSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Session_ID")
    private int sessionId;

    @Column(name = "Venue")
    private String venue;

    @Column(name = "Group")
    private String group;

    @Column(name = "Time")
    private String time;

    @Column(name = "Quota")
    private int quota;

    @ManyToOne
    private Sport sport;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "sportSession", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    public SportSession() {}

    public SportSession(String venue, String group, String time, int quota, Sport sport, Teacher teacher, List<Registration> registrations) {
        this.venue = venue;
        this.group = group;
        this.time = time;
        this.quota = quota;
        this.sport = sport;
        this.teacher = teacher;
        this.registrations = registrations;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "SportSession{" +
                "sessionId=" + sessionId +
                ", venue='" + venue + '\'' +
                ", group='" + group + '\'' +
                ", time='" + time + '\'' +
                ", quota=" + quota +
                ", sport=" + sport +
                ", teacher=" + teacher +
                ", registrations=" + registrations +
                '}';
    }
}
