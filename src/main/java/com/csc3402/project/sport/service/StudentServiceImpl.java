package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.Student;
import com.csc3402.project.sport.repository.StudentRepository;
import com.csc3402.project.sport.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> findStudentById(int id) {
        return studentRepository.findById(id);
    }

    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    public Student findStudentByUsername(String username) {
        return studentRepository.findByEmail(username);
    }
}

