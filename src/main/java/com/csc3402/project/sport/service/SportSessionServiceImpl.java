package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.SportSession;
import com.csc3402.project.sport.repository.SportSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportSessionServiceImpl implements SportSessionService {

    private final SportSessionRepository sportSessionRepository;

    public SportSessionServiceImpl(SportSessionRepository sportSessionRepository) {
        this.sportSessionRepository = sportSessionRepository;
    }

    @Override
    public List<SportSession> listAllSessions() {
        return sportSessionRepository.findAll();
    }

    @Override
    public Optional<SportSession> findSessionById(String id) {
        return sportSessionRepository.findById(id);
    }

    @Override
    public SportSession addOrUpdateSession(SportSession session) {
        return sportSessionRepository.save(session);
    }

    @Override
    public void deleteSession(SportSession session) {
        sportSessionRepository.delete(session);
    }

    @Override
    public List<SportSession> findSessionsByTeacherId(String teacherId) {
        return sportSessionRepository.findByTeacher_TeacherId(teacherId);
    }
    @Override
    public List<SportSession> searchSessions(String query) {
        return sportSessionRepository.searchBySportOrSessionId(query);
    }

}
