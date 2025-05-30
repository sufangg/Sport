package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.SportSession;
import com.csc3402.project.sport.repository.SportSessionRepository;
import com.csc3402.project.sport.service.SportSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportSessionServiceImpl implements SportSessionService {

    @Autowired
    private SportSessionRepository sportSessionRepository;

    public List<SportSession> listAllSessions() {
        return sportSessionRepository.findAll();
    }

    public Optional<SportSession> findSessionById(int id) {
        return sportSessionRepository.findById(id);
    }

    public SportSession addOrUpdateSession(SportSession session) {
        return sportSessionRepository.save(session);
    }

    public void deleteSession(SportSession session) {
        sportSessionRepository.delete(session);
    }
}

