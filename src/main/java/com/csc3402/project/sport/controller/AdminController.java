// AdminController.java
package com.csc3402.project.sport.controller;

import com.csc3402.project.sport.model.Admin;
import com.csc3402.project.sport.model.Sport;
import com.csc3402.project.sport.model.Teacher;
import com.csc3402.project.sport.model.SportSession;
import com.csc3402.project.sport.repository.SportRepository;
import com.csc3402.project.sport.repository.TeacherRepository;
import com.csc3402.project.sport.repository.SportSessionRepository;
import com.csc3402.project.sport.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SportSessionRepository sessionRepo;

    @Autowired
    private SportRepository sportRepo;

    @Autowired
    private TeacherRepository teacherRepo;

    @Autowired
    private AdminService adminService;

    @GetMapping("/home")
    public String showAdminHome(Model model, Principal principal) {
        String name = (principal != null) ? principal.getName() : "admin1";
        Admin admin = adminService.findAdminByName(name);
        model.addAttribute("admin", admin);
        return "admin-home";
    }

    @GetMapping("/sessions")
    public String viewSessions(Model model) {
        model.addAttribute("sessions", sessionRepo.findAll());
        return "session-list";
    }

    @GetMapping("/add-session")
    public String showAddSessionForm(Model model) {
        model.addAttribute("session", new SportSession());
        return "add-session";
    }

    @PostMapping("/add-session")
    public String saveSession(@RequestParam String sportId,
                              @RequestParam String sportName,
                              @RequestParam String sessionId,
                              @RequestParam String sessionGroup,
                              @RequestParam String venue,
                              @RequestParam String sessionTime,
                              @RequestParam int quota,
                              @RequestParam String teacherName) {

        // Try to find or create sport
        Sport sport = sportRepo.findById(sportId).orElse(new Sport());
        sport.setSportId(sportId);
        sport.setSportName(sportName);
        sportRepo.save(sport);

        // Try to find or create teacher
        Teacher teacher = teacherRepo.findByName(teacherName);
        if (teacher == null) {
            teacher = new Teacher();
            teacher.setTeacherId("T" + System.currentTimeMillis());
            teacher.setName(teacherName);
            teacher.setEmail(teacherName.toLowerCase().replaceAll(" ", "") + "@school.edu");
            teacher.setPassword("default123");
            teacher.setPhoneNumber("0000000000");
            teacher.setRoomNumber("Unknown");
            teacherRepo.save(teacher);
        }

        // Create new session
        SportSession session = new SportSession();
        session.setSessionId(sessionId);
        session.setSessionGroup(sessionGroup);
        session.setVenue(venue);
        session.setSessionTime(sessionTime);
        session.setQuota(quota);
        session.setSport(sport);
        session.setTeacher(teacher);

        sessionRepo.save(session);
        return "redirect:/admin/sessions";
    }
}