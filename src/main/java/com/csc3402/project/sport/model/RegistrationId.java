package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RegistrationId implements Serializable {

    @Column(name = "Student_ID")
    private String studentId;

    @Column(name = "Session_ID")
    private String sessionId;

    public RegistrationId() {}

    public RegistrationId(String studentId, String sessionId) {
        this.studentId = studentId;
        this.sessionId = sessionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationId)) return false;
        RegistrationId that = (RegistrationId) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, sessionId);
    }
}
