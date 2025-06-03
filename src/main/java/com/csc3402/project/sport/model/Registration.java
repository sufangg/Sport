package com.csc3402.project.sport.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Registration {

    @EmbeddedId
    private RegistrationId id;

    @Column(name = "Semester", insertable = false, updatable = false)
    private String semester;

    @Column(name = "Registration_Date")
    private Date registrationDate;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "Student_ID")
    private Student student;

    @ManyToOne
    @MapsId("sessionId")
    @JoinColumn(name = "Session_ID")
    private SportSession sportSession;

    public Registration() {}

    public Registration(Student student, SportSession session, String semester, Date date) {
        this.id = new RegistrationId(student.getStudentId(), session.getSessionId(), semester);
        this.semester = semester;
        this.registrationDate = date;
        this.student = student;
        this.sportSession = session;
    }


    public RegistrationId getId() {
        return id;
    }

    public void setId(RegistrationId id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SportSession getSportSession() {
        return sportSession;
    }

    public void setSportSession(SportSession sportSession) {
        this.sportSession = sportSession;
    }
}
