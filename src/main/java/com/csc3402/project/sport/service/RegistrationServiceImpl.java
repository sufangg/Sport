package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.*;
import com.csc3402.project.sport.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository,
                                   StudentRepository studentRepository) {
        this.registrationRepository = registrationRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Registration> listAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public List<Registration> findRegistrationsByStudent(Student student) {
        return registrationRepository.findByStudent(student);
    }

    @Override
    public List<Registration> findRegistrationsByUsername(String username) {
        Student student = studentRepository.findByEmail(username);
        return registrationRepository.findByStudent(student);
    }

    @Override
    public List<Student> findStudentsBySessionId(String sessionId) {
        return registrationRepository.findBySportSession_SessionId(sessionId)
                .stream()
                .map(Registration::getStudent)
                .collect(Collectors.toList());
    }

    @Override
    public Registration registerStudent(Registration registration) {
        return registrationRepository.save(registration);
    }

    @Override
    public void dropRegistration(RegistrationId id) {
        registrationRepository.deleteById(id);
    }






}
