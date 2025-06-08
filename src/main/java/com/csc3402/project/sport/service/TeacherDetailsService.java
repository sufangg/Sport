/*package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.Teacher;
import com.csc3402.project.sport.repository.TeacherRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TeacherDetailsService implements UserDetailsService {

    private final TeacherRepository teacherRepository;

    public TeacherDetailsService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findByEmail(email);

        return new User(
                teacher.getEmail(),
                teacher.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_TEACHER"))
        );
    }
}*/