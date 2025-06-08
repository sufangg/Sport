/*package com.csc3402.project.sport.controller;

import com.csc3402.project.sport.model.Teacher;
import com.csc3402.project.sport.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // 🔹 Teacher homepage
    @GetMapping("/home")
    public String teacherHome(Principal principal, Model model) {
        String email = principal.getName(); // Logged-in teacher
        Teacher teacher = teacherService.findTeacherByUsername(email);
        model.addAttribute("teacher", teacher);
        return "teacher-home"; // HTML page under templates/teacher/
    }

    // 🔹 View profile
    @GetMapping("/profile")
    public String viewProfile(Principal principal, Model model) {
        String email = principal.getName();
        Teacher teacher = teacherService.findTeacherByUsername(email);
        model.addAttribute("teacher", teacher);
        return "teacher-profile";
    }

    // 🔹 Edit profile form
    @GetMapping("/edit")
    public String showEditForm(Principal principal, Model model) {
        String email = principal.getName();
        Teacher teacher = teacherService.findTeacherByUsername(email);
        model.addAttribute("teacher", teacher);
        return "teacher-edit";
    }

    // 🔹 Submit edit form
    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute("teacher") Teacher updatedTeacher, Principal principal) {
        String email = principal.getName();
        Teacher existing = teacherService.findTeacherByUsername(email);
        teacherService.updateTeacher(existing.getTeacherId(), updatedTeacher);
        return "redirect:/teacher/profile";
    }

    // 🔹 Registration form (Admin adds teacher)
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-register";
    }

    // 🔹 Submit new teacher registration
    @PostMapping("/register")
    public String registerTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:/admin/home"; // You can change redirect if needed
    }
}*/