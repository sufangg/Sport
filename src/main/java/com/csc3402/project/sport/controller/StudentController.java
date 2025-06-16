package com.csc3402.project.sport.controller;

import com.csc3402.project.sport.model.*;
import com.csc3402.project.sport.repository.StudentRepository;
import com.csc3402.project.sport.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        Student student = studentService.findStudentByUsername(username);
        if (student == null) {
            model.addAttribute("studentName", "Student");
        } else {
            model.addAttribute("student", student);
        }

        return "student-home";
    }


    @GetMapping("register")
    public String showRegisterPage(Model model) {
        List<SportSession> sessions = sportSessionService.listAllSessions();
        model.addAttribute("sessions", sessions);
        return "student-register";
    }

    @PostMapping("/register")
    public String registerSession(@RequestParam("sessionId") String sessionId,
                                  Principal principal,
                                  Model model) {
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
            model.addAttribute("error", "Session is full.");
            model.addAttribute("sessions", sportSessionService.listAllSessions());
            return "student-register";
        }

        RegistrationId regId = new RegistrationId(student.getStudentId(), sessionId, student.getSemester());
        Registration reg = new Registration(student, session, student.getSemester(), new Date());
        reg.setId(regId);
        registrationService.registerStudent(reg);

        return "redirect:/student/home";
    }


    @GetMapping("drop/{sessionId}")
    public String dropSession(@PathVariable("sessionId") String sessionId, Principal principal) {
        String username = (principal != null) ? principal.getName() : "ali@example.com";
        Student student = studentService.findStudentByUsername(username);
        RegistrationId regId = new RegistrationId(student.getStudentId(), sessionId, student.getSemester());
        registrationService.dropRegistration(regId);
        return "redirect:/student/home";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<SportSession> sessions = sportSessionService.searchSessions(query);
        if (sessions.isEmpty()) {
            model.addAttribute("error", "No sport sessions found. Showing all sessions shortly...");
            model.addAttribute("showAll", true); // signal for JS to fetch all later
            return "student-register";
        }
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
    public String showEditForm(@PathVariable("studentId") String studentId,
                               Model model,
                               Principal principal) {

        // Get email from logged-in user
        String email = principal.getName();

        // Find the logged-in student
        Student currentStudent = studentService.findStudentByUsername(email);

        // Block if logged-in student does not match the one being edited
        if (currentStudent == null || !studentId.equals(currentStudent.getStudentId())) {
            return "redirect:/access-denied";
        }

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
                                Model model,
                                Principal principal) {

        // Get logged-in email
        String email = principal.getName();

        // Retrieve logged-in student by email
        Student currentStudent = studentService.findStudentByUsername(email);

        if (currentStudent == null) {
            return "redirect:/login";
        }

        // Check if the logged-in student's ID matches the form student's ID
        if (!studentId.equals(currentStudent.getStudentId())) {
            return "redirect:/access-denied";  // Access block
        }

        // Prevent tampering: force-set the correct studentId on submitted object
        student.setStudentId(currentStudent.getStudentId());

        if (result.hasErrors()) {
            return "student-edit";
        }

        studentService.updateStudent(studentId, student);
        return "redirect:/student/profile";
    }

    @GetMapping("/drop/{studentId}/{sessionId}/{semester}")
    public String dropRegistration(@PathVariable String studentId,
                                   @PathVariable String sessionId,
                                   @PathVariable String semester) {
        RegistrationId regId = new RegistrationId(studentId, sessionId, semester);
        registrationService.dropRegistration(regId);
        return "redirect:/student/home";
    }


    @GetMapping("session/{id}/students")
    public String viewStudentsBySession(@PathVariable String id, Model model) {
        List<Student> students = registrationService.findStudentsBySessionId(id);
        model.addAttribute("students", students);
        return "student-list";
    }

}
