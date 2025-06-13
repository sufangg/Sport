package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class SportSession {
    @Id
    @Column(name = "Session_ID")
    private String sessionId;

    @Column(name = "Venue")
    private String venue;

    @Column(name = "Session_Group")
    private String sessionGroup;

    @Column(name = "Session_Time")
    private String sessionTime;

    @Column(name = "Quota")
    private int quota;

    @ManyToOne
    private Sport sport;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "sportSession", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    public SportSession() {}

    public SportSession(String sessionId, String venue, String sessionGroup, String sessionTime, int quota, Sport sport, Teacher teacher, List<Registration> registrations) {
        this.sessionId = sessionId;
        this.venue = venue;
        this.sessionGroup = sessionGroup;
        this.sessionTime = sessionTime;
        this.quota = quota;
        this.sport = sport;
        this.teacher = teacher;
        this.registrations = registrations;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getSessionGroup() {
        return sessionGroup;
    }

    public void setSessionGroup(String sessionGroup) {
        this.sessionGroup = sessionGroup;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
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

    @Override
    public String toString() {
        return "SportSession{" +
                "sessionId=" + sessionId +
                ", venue='" + venue + '\'' +
                ", sessionGroup='" + sessionGroup + '\'' +
                ", sessionTime='" + sessionTime + '\'' +
                ", quota=" + quota +
                ", sport=" + sport +
                ", teacher=" + teacher +
                ", registrations=" + registrations +
                '}';
    }
}
