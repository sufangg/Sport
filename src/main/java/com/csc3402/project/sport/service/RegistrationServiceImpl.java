package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.*;
import com.csc3402.project.sport.repository.*;
import com.csc3402.project.sport.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Registration> listAllRegistrations() {
        return registrationRepository.findAll();
    }

    public List<Registration> findRegistrationsByStudent(Student student) {
        return registrationRepository.findByStudent(student);
    }

    public List<Registration> findRegistrationsByUsername(String username) {
        Student student = studentRepository.findByEmail(username);
        return registrationRepository.findByStudent(student);
    }

    public List<Student> findStudentsBySessionId(int sessionId) {
        return registrationRepository.findBySportSession_SessionId(sessionId)
                .stream()
                .map(Registration::getStudent)
                .collect(Collectors.toList());
    }

    public Registration registerStudent(Registration registration) {
        return registrationRepository.save(registration);
    }

    public void dropRegistration(RegistrationId id) {
        registrationRepository.deleteById(id);
    }
}

