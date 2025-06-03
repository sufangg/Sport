package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> findStudentById(String id);
    List<Student> listAllStudents();
    Student addNewStudent(Student student);
    Student updateStudent(String studentId, Student student);
    void deleteStudent(Student student);
    Student findStudentByUsername(String username);
    void saveStudent(Student student);
}

