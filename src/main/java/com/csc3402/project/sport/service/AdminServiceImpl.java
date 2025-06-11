package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.Admin;
import com.csc3402.project.sport.model.SportSession;
import com.csc3402.project.sport.repository.AdminRepository;
import com.csc3402.project.sport.repository.SportSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final SportSessionRepository sessionRepository;
    private final AdminRepository adminRepository;

    public AdminServiceImpl(SportSessionRepository sessionRepository, AdminRepository adminRepository) {
        this.sessionRepository = sessionRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public List<SportSession> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public void addNewSession(SportSession session) {
        sessionRepository.save(session);
    }

    @Override
    public Admin findAdminByName(String name) {
        return adminRepository.findByName(name);
    }
}
