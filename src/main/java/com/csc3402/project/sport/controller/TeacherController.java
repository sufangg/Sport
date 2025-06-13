package com.csc3402.project.sport.controller;

import com.csc3402.project.sport.model.SportSession;
import com.csc3402.project.sport.model.Teacher;
import com.csc3402.project.sport.service.SportSessionService;
import com.csc3402.project.sport.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final SportSessionService sportSessionService;

    @Autowired
    public TeacherController(TeacherService teacherService, SportSessionService sportSessionService) {
        this.teacherService = teacherService;
        this.sportSessionService = sportSessionService;
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
}