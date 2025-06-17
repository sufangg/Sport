package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.SportSession;
import com.csc3402.project.sport.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface SportSessionService {
    List<SportSession> listAllSessions();
    Optional<SportSession> findSessionById(String id);
    List<SportSession> findSessionsByTeacherId(String teacherId);
    SportSession addOrUpdateSession(SportSession session);
    void deleteSession(SportSession session);
    List<SportSession> searchSessions(String query);
    List<SportSession> findByTeacher(Teacher teacher);
    List<SportSession> findBySportName(String sportName);

}
