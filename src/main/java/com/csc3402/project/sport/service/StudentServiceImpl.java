package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.Student;
import com.csc3402.project.sport.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> findStudentById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addNewStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(String studentId, Student student) {
        Student existingStudent = findStudentById(studentId).orElseThrow();

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setPhoneNumber(student.getPhoneNumber());

        if (!student.getPassword().isBlank()) {
            existingStudent.setPassword(student.getPassword());
        }

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student findStudentByUsername(String username) {
        return studentRepository.findByEmail(username);
    }
}
