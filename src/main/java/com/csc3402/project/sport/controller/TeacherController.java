package com.csc3402.project.sport.controller;

import com.csc3402.project.sport.model.*;
import com.csc3402.project.sport.service.RegistrationService;
import com.csc3402.project.sport.service.SportSessionService;
import com.csc3402.project.sport.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final SportSessionService sportSessionService;
    private final RegistrationService registrationService;

    @Autowired
    public TeacherController(
            TeacherService teacherService,
            SportSessionService sportSessionService,
            RegistrationService registrationService) {
        this.teacherService = teacherService;
        this.sportSessionService = sportSessionService;
        this.registrationService = registrationService;
    }

    // Show teacher dashboard/home
    @GetMapping("/home")
    public String teacherHome(Model model, Principal principal) {
        String username = (principal != null) ? principal.getName() : "lim@school.edu";

        Teacher teacher = teacherService.findTeacherByUsername(username);
        if (teacher == null) {
            model.addAttribute("teacherName", "Teacher");
        } else {
            model.addAttribute("teacher", teacher);
        }

        List<SportSession> sportSessions = sportSessionService.findByTeacher(teacher);
        model.addAttribute("sportSessions", sportSessions);

        return "teacher-home";
    }

    // Show students registered in a specific session
    @GetMapping("/sessions/{sessionId}/students")
    public String viewStudentsInSession(@PathVariable("sessionId") String sessionId, Model model) {
        List<Registration> registrations = registrationService.findRegistrationsBySessionId(sessionId);
        model.addAttribute("registrations", registrations);
        model.addAttribute("sessionId", sessionId);
        return "teacher-view-student";
    }

    // Show teacher profile
    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        String username = (principal != null) ? principal.getName() : "lim@school.edu";
        Teacher teacher = teacherService.findTeacherByUsername(username);
        if (teacher == null) {
            throw new RuntimeException("No teacher found with email: " + username);
        }

        model.addAttribute("teacher", teacher);
        return "teacher-profile";
    }

    // Show edit form
    @GetMapping("/edit/{teacherId}")
    public String showEditForm(@PathVariable("teacherId") String teacherId, Model model) {
        Teacher teacher = teacherService.findTeacherById(teacherId).orElse(null);
        if (teacher == null) {
            return "redirect:/teacher/profile";
        }
        model.addAttribute("teacher", teacher);
        return "teacher-edit";
    }

    // Handle edit form submission
    @PostMapping("/edit/{teacherId}")
    public String updateTeacher(@PathVariable("teacherId") String teacherId,
                                @ModelAttribute("teacher") Teacher updatedTeacher) {
        teacherService.updateTeacher(teacherId, updatedTeacher);
        return "redirect:/teacher/profile";
    }

    // Show register account form
    @GetMapping("/account")
    public String showAccountForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-account";
    }

    // Handle register form
    @PostMapping("/account")
    public String registerTeacher(@ModelAttribute("teacher") Teacher teacher, Model model) {
        teacherService.saveTeacher(teacher);
        model.addAttribute("message", "Account created successfully!");
        return "redirect:/LoginPage";
    }


    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        List<SportSession> sessions = sportSessionService.listAllSessions();
        model.addAttribute("sessions", sessions);
        return "teacher-register";
    }

    @PostMapping("/register")
    public String registerSession(@RequestParam("sessionId") String sessionId,
                                  Principal principal,
                                  Model model) {
        String username = (principal != null) ? principal.getName() : "ali@example.com";

        Teacher teacher = teacherService.findTeacherByUsername(username);
        SportSession session = sportSessionService.findSessionById(sessionId).orElse(null);

        List<SportSession> existingSessions = sportSessionService.findByTeacher(teacher);
        if (!existingSessions.isEmpty()) {
            model.addAttribute("error", "You are already registered to a session.");
            model.addAttribute("sessions", sportSessionService.listAllSessions());
            return "teacher-register";
        }

        if (session == null) {
            model.addAttribute("error", "Session not found.");
            model.addAttribute("sessions", sportSessionService.listAllSessions());
            return "teacher-register";
        }

        if (session.getTeacher() != null) {
            model.addAttribute("error", "This session already has a teacher assigned.");
            model.addAttribute("sessions", sportSessionService.listAllSessions());
            return "teacher-register";
        }

        session.setTeacher(teacher);
        sportSessionService.addOrUpdateSession(session);

        return "redirect:/teacher/register";
    }

    @GetMapping("/drop/{sessionId}")
    public String unassignTeacher(@PathVariable("sessionId") String sessionId, Principal principal) {
        String email = principal.getName();
        Teacher teacher = teacherService.findTeacherByUsername(email);

        SportSession session = sportSessionService.findSessionById(sessionId).orElse(null);

        if (session != null && session.getTeacher() != null &&
                session.getTeacher().getTeacherId().equals(teacher.getTeacherId())) {

            session.setTeacher(null);
            sportSessionService.addOrUpdateSession(session);
        }

        return "redirect:/teacher/home";
    }


    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<SportSession> sessions = sportSessionService.searchSessions(query);
        if (sessions.isEmpty()) {
            model.addAttribute("error", "No sport sessions found. Showing all sessions shortly...");
            model.addAttribute("showAll", true); // signal for JS to fetch all later
            return "teacher-register";
        }
        model.addAttribute("sessions", sessions);
        return "teacher-register";
    }


}