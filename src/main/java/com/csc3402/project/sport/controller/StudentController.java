package com.csc3402.project.sport.controller;

import com.csc3402.project.sport.model.*;
import com.csc3402.project.sport.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final SportSessionService sportSessionService;
    private final RegistrationService registrationService;

    public StudentController(StudentService studentService, SportSessionService sportSessionService, RegistrationService registrationService) {
        this.studentService = studentService;
        this.sportSessionService = sportSessionService;
        this.registrationService = registrationService;
    }

    @GetMapping("home")
    public String studentHome(Model model, Principal principal) {
        String username = (principal != null) ? principal.getName() : "ali@example.com";
        List<Registration> registrations = registrationService.findRegistrationsByUsername(username);
        model.addAttribute("registrations", registrations);
        return "student-home";
    }

    @GetMapping("register")
    public String showRegisterPage(Model model) {
        List<SportSession> sessions = sportSessionService.listAllSessions();
        model.addAttribute("sessions", sessions);
        return "student-register";
    }

    @PostMapping("register")
    public String registerSession(@RequestParam("sessionId") String sessionId, Principal principal, Model model) {
        String username = (principal != null) ? principal.getName() : "ali@example.com";
        Student student = studentService.findStudentByUsername(username);

        List<Registration> existing = registrationService.findRegistrationsByStudent(student);
        if (!existing.isEmpty()) {
            model.addAttribute("error", "You have already registered for a sport this semester.");
            model.addAttribute("sessions", sportSessionService.listAllSessions());
            return "student-register";
        }

        SportSession session = sportSessionService.findSessionById(sessionId).orElse(null);
        if (session == null || session.getRegistrations().size() >= session.getQuota()) {
            model.addAttribute("error", "Session is full or does not exist.");
            model.addAttribute("sessions", sportSessionService.listAllSessions());
            return "student-register";
        }

        RegistrationId regId = new RegistrationId(student.getStudentId(), sessionId);
        Registration reg = new Registration("May 2025", new Date(), student, session);
        reg.setId(regId);
        registrationService.registerStudent(reg);

        return "redirect:/student/home";
    }

    @GetMapping("drop/{sessionId}")
    public String dropSession(@PathVariable("sessionId") String sessionId, Principal principal) {
        String username = (principal != null) ? principal.getName() : "ali@example.com";
        Student student = studentService.findStudentByUsername(username);
        RegistrationId regId = new RegistrationId(student.getStudentId(), sessionId);
        registrationService.dropRegistration(regId);
        return "redirect:/student/home";
    }

    @GetMapping("session/{id}/students")
    public String viewStudentsBySession(@PathVariable String id, Model model) {
        List<Student> students = registrationService.findStudentsBySessionId(id);
        model.addAttribute("students", students);
        return "session-students";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<SportSession> sessions = sportSessionService.searchSessions(query);
        model.addAttribute("sessions", sessions);
        return "student-register";
    }

    @GetMapping("/profile")
    public String showStudentProfile(Model model, Principal principal) {
        String username = (principal != null) ? principal.getName() : "ali@example.com";
        Student student = studentService.findStudentByUsername(username);
        model.addAttribute("student", student);
        return "student-profile";
    }

    @GetMapping("/edit/{studentId}")
    public String showEditForm(@PathVariable("studentId") String studentId, Model model) {
        Optional<Student> optionalStudent = studentService.findStudentById(studentId);
        if (optionalStudent.isEmpty()) {
            return "redirect:/student/profile";
        }
        model.addAttribute("student", optionalStudent.get());
        return "student-edit";
    }

    @PostMapping("/edit/{studentId}")
    public String updateStudent(@PathVariable("studentId") String studentId,
                                @ModelAttribute("student") @Valid Student student,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "student-edit";
        }

        studentService.updateStudent(studentId, student);
        return "redirect:/student/profile";
    }



}
