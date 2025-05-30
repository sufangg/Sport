package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.*;
import java.util.List;

public interface RegistrationService {
    List<Registration> listAllRegistrations();
    List<Registration> findRegistrationsByStudent(Student student);
    List<Registration> findRegistrationsByUsername(String username);
    List<Student> findStudentsBySessionId(int sessionId);
    Registration registerStudent(Registration registration);
    void dropRegistration(RegistrationId id);
}
