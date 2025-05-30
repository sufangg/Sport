package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RegistrationId implements Serializable {

    @Column(name = "Student_ID")
    private Integer studentId;

    @Column(name = "Session_ID")
    private Integer sessionId;

    public RegistrationId() {}

    public RegistrationId(Integer studentId, Integer sessionId) {
        this.studentId = studentId;
        this.sessionId = sessionId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
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
