package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.SportSession;
import java.util.List;
import java.util.Optional;

public interface SportSessionService {
    List<SportSession> listAllSessions();
    Optional<SportSession> findSessionById(String id);
    SportSession addOrUpdateSession(SportSession session);
    void deleteSession(SportSession session);
    List<SportSession> searchSessions(String query);
}
