package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.Teacher;
import com.csc3402.project.sport.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Optional<Teacher> findTeacherById(String id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> listAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher addNewTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(String teacherId, Teacher teacher) {
        Teacher existing = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacherId));

        existing.setName(teacher.getName());
        existing.setEmail(teacher.getEmail());
        existing.setPhoneNumber(teacher.getPhoneNumber());

        if (teacher.getPassword() != null && !teacher.getPassword().isBlank()) {
            existing.setPassword(teacher.getPassword());
        }

        return teacherRepository.save(existing);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher findTeacherByUsername(String username) {
        Teacher teacher = teacherRepository.findByEmail(username);
        if (teacher == null) {
            throw new RuntimeException("No teacher found with email: " + username);
        }
        return teacher;
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}