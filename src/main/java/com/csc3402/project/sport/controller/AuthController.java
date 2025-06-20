package com.csc3402.project.sport.controller;

import com.csc3402.project.sport.dto.UserRegistrationDto;
import com.csc3402.project.sport.model.Student;
import com.csc3402.project.sport.model.Teacher;
import com.csc3402.project.sport.repository.StudentRepository;
import com.csc3402.project.sport.repository.TeacherRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;

@Controller
public class AuthController {

    private final StudentRepository studentRepo;
    private final TeacherRepository teacherRepo;

    public AuthController(StudentRepository studentRepo, TeacherRepository teacherRepo) {
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "success", required = false) String success,
                            Model model) {
        if (success != null) {
            model.addAttribute("success", "Account created successfully.");
        }
        return "login";
    }

    @GetMapping("/user-registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "user-registration";
    }

    @PostMapping("/user-registration")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto dto,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        // Check for duplicate email
        if (emailExists(dto.getEmail())) {
            model.addAttribute("error", "Email already exists.");
            return "user-registration";
        }

        if ("STUDENT".equalsIgnoreCase(dto.getRole())) {
            Student student = new Student();
            student.setStudentId(generateStudentId());
            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setPassword(dto.getPassword());
            student.setPhoneNumber(dto.getPhoneNumber().replace(",", "").trim());
            student.setSemester(dto.getSemester());
            student.setAddress(dto.getAddress());
            studentRepo.save(student);
        } else if ("TEACHER".equalsIgnoreCase(dto.getRole())) {
            Teacher teacher = new Teacher();
            teacher.setTeacherId(generateTeacherId());
            teacher.setName(dto.getName());
            teacher.setEmail(dto.getEmail());
            teacher.setPassword(dto.getPassword());
            teacher.setPhoneNumber(dto.getPhoneNumber().replace(",", "").trim());
            teacher.setRoomNumber(dto.getRoomNumber());
            teacherRepo.save(teacher);
        }


        redirectAttributes.addAttribute("success", "true");
        return "redirect:/login";
    }

    private boolean emailExists(String email) {
        return studentRepo.findByEmail(email) != null ||
                teacherRepo.findByEmail(email) != null;
    }

    private String generateStudentId() {
        long count = studentRepo.count() + 1;
        return String.format("S%03d", count);
    }

    private String generateTeacherId() {
        long count = teacherRepo.count() + 1;
        return String.format("T%03d", count);
    }

    @GetMapping("/access-denied")
    public String accessDeniedRedirect(Principal principal, Model model) {
        if (principal != null) {
            // Get roles
            Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
            boolean isStudent = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"));
            boolean isTeacher = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"));
            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            if (isStudent) {
                model.addAttribute("homeLink", "/student/home");
            } else if (isTeacher) {
                model.addAttribute("homeLink", "/teacher/home");
            } else if (isAdmin){
                model.addAttribute("homeLink", "/admin/home");
            }
        }
        return "access-denied";
    }


}
