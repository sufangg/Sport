package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.Admin;
import com.csc3402.project.sport.model.Student;
import com.csc3402.project.sport.model.Teacher;
import com.csc3402.project.sport.repository.AdminRepository;
import com.csc3402.project.sport.repository.StudentRepository;
import com.csc3402.project.sport.repository.TeacherRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepo;
    private final TeacherRepository teacherRepo;
    private final StudentRepository studentRepo;

    public CustomUserDetailsService(AdminRepository adminRepo, TeacherRepository teacherRepo, StudentRepository studentRepo) {
        this.adminRepo = adminRepo;
        this.teacherRepo = teacherRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByAdminId(username);
        if (admin != null) {
            return new User(admin.getAdminId(), admin.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }

        Teacher teacher = teacherRepo.findByEmail(username);
        if (teacher != null) {
            return new User(teacher.getEmail(), teacher.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_TEACHER")));
        }

        Student student = studentRepo.findByEmail(username);
        if (student != null) {
            return new User(student.getEmail(), student.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT")));
        }

        throw new UsernameNotFoundException("User not found");
    }
}