package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.Admin;
import com.csc3402.project.sport.model.SportSession;

import java.util.List;

public interface AdminService {
    List<SportSession> getAllSessions();
    void addNewSession(SportSession session);

    Admin findAdminByName(String name);
    Admin findByAdminId(String adminId);
}
