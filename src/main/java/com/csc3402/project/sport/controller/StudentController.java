package com.csc3402.project.sport.controller;

import com.csc3402.project.sport.model.*;
import com.csc3402.project.sport.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

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
        List<Registration> registrations = registrationService.findRegistrationsByUsername(principal.getName());
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
    public String registerSession(@RequestParam("sessionId") Integer sessionId, Principal principal, Model model) {
        Student student = studentService.findStudentByUsername(principal.getName());
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
        Registration reg = new Registration();
        reg.setId(regId);
        reg.setStudent(student);
        reg.setSportSession(session);
        reg.setSemester("May 2025");
        reg.setRegistrationDate(new Date());
        registrationService.registerStudent(reg);

        return "student-home";
    }

    @GetMapping("drop/{sessionId}")
    public String dropSession(@PathVariable("sessionId") int sessionId, Principal principal) {
        Student student = studentService.findStudentByUsername(principal.getName());
        RegistrationId regId = new RegistrationId(student.getStudentId(), sessionId);
        registrationService.dropRegistration(regId);
        return "student-home";
    }

    @GetMapping("session/{id}/students")
    public String viewStudentsBySession(@PathVariable int id, Model model) {
        List<Student> students = registrationService.findStudentsBySessionId(id);
        model.addAttribute("students", students);
        return "session-students";
    }
}
