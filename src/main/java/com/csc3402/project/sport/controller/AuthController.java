package com.csc3402.project.sport.controller;

import com.csc3402.project.sport.dto.UserRegistrationDto;
import com.csc3402.project.sport.model.Student;
import com.csc3402.project.sport.model.Teacher;
import com.csc3402.project.sport.repository.StudentRepository;
import com.csc3402.project.sport.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final StudentRepository studentRepo;
    private final TeacherRepository teacherRepo;

    public AuthController(StudentRepository studentRepo, TeacherRepository teacherRepo) {
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user-registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "user-registration";
    }

    @PostMapping("/user-registration")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto dto, Model model) {
        if ("STUDENT".equalsIgnoreCase(dto.getRole())) {
            Student student = new Student();
            student.setStudentId(generateStudentId());
            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setPassword(dto.getPassword());
            student.setPhoneNumber(dto.getPhoneNumber());
            student.setSemester(dto.getSemester());
            student.setAddress(dto.getAddress());
            studentRepo.save(student);
        } else if ("TEACHER".equalsIgnoreCase(dto.getRole())) {
            Teacher teacher = new Teacher();
            teacher.setTeacherId(generateTeacherId());
            teacher.setName(dto.getName());
            teacher.setEmail(dto.getEmail());
            teacher.setPassword(dto.getPassword());
            teacher.setPhoneNumber(dto.getPhoneNumber());
            teacher.setRoomNumber(dto.getRoomNumber());
            teacherRepo.save(teacher);
        }

        model.addAttribute("success", "Account created successfully. Please login.");
        return "redirect:/login";
    }

    private String generateStudentId() {
        long count = studentRepo.count() + 1;
        return String.format("S%03d", count); // e.g., S001
    }

    private String generateTeacherId() {
        long count = teacherRepo.count() + 1;
        return String.format("T%03d", count); // e.g., T001
    }
}