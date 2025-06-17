// AdminController.java
package com.csc3402.project.sport.controller;

import com.csc3402.project.sport.model.*;
import com.csc3402.project.sport.repository.RegistrationRepository;
import com.csc3402.project.sport.repository.SportRepository;
import com.csc3402.project.sport.repository.TeacherRepository;
import com.csc3402.project.sport.repository.SportSessionRepository;
import com.csc3402.project.sport.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private SportSessionRepository sessionRepo;

    @Autowired
    private SportRepository sportRepo;

    @Autowired
    private TeacherRepository teacherRepo;

    @Autowired
    private AdminService adminService;
    @Autowired
    private SportSessionRepository sportSessionRepository;
    @Autowired
    private SportRepository sportRepository;

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

    //@GetMapping("/admin/view-registered/{sessionId}")
    @GetMapping("/sessions/{sessionId}/students")
    public String viewRegisteredStudents(@PathVariable String sessionId, Model model) {
        List<Registration> registrations = registrationRepository.findBySportSession_SessionId(sessionId);

        List<Student> studentList = registrations.stream()
                .map(Registration::getStudent)
                .toList(); // Or use .collect(Collectors.toList()); if you're using Java 8-16

        model.addAttribute("students", studentList);
        model.addAttribute("sessionId", sessionId);
        return "registered-students";
    }



    @GetMapping("/add-session")
    public String showAddSessionForm(Model model) {
        model.addAttribute("session", new SportSession());
        return "add-session";
    }

    @PostMapping("/add-session")
    public String saveSession(@RequestParam String sportName,
                              @RequestParam(required = false) String sessionId,
                              @RequestParam String sessionGroup,
                              @RequestParam String venue,
                              @RequestParam String sessionTime,
                              @RequestParam int quota,
                              Model model) {

        // Find or create sport
        Sport sport = sportRepository.findBySportName(sportName);
        if (sport == null) {
            sport = new Sport();
            sport.setSportId(generateSportId());
            sport.setSportName(sportName);
            sportRepository.save(sport);
        }

        // Check if this sport already has the group
        List<SportSession> sessions = sportSessionRepository.findBySport_SportNameIgnoreCase(sportName);
        boolean groupExists = sessions.stream()
                .anyMatch(s -> s.getSessionGroup().equalsIgnoreCase(sessionGroup));

        if (groupExists) {
            // Suggest next group
            int maxGroup = sessions.stream()
                    .map(SportSession::getSessionGroup)
                    .filter(g -> g.matches("G\\d+"))
                    .map(g -> Integer.parseInt(g.substring(1)))
                    .max(Integer::compareTo)
                    .orElse(0);

            String suggestedGroup = "G" + (maxGroup + 1);

            model.addAttribute("error", "This group already exists for " + sportName + ".");
            model.addAttribute("suggestedGroup", suggestedGroup);
            model.addAttribute("sportName", sportName); // re-fill form
            model.addAttribute("sessionGroup", sessionGroup);
            model.addAttribute("venue", venue);
            model.addAttribute("sessionTime", sessionTime);
            model.addAttribute("quota", quota);
            return "add-session"; // Return to the form with error
        }

        // Generate sessionId if not provided
        if (sessionId == null || sessionId.isEmpty()) {
            sessionId = generateSessionId();
        }

        // Create and save session
        SportSession session = new SportSession();
        session.setSessionId(sessionId);
        session.setSessionGroup(sessionGroup);
        session.setVenue(venue);
        session.setSessionTime(sessionTime);
        session.setQuota(quota);
        session.setSport(sport);
        session.setTeacher(null);

        sessionRepo.save(session);
        return "redirect:/admin/sessions";
    }


    private String generateSportId() {
        long count = sportRepo.count() + 1;
        return String.format("SP%02d", count); // e.g., SP01, SP02
    }

    private String generateSessionId() {
        long count = sessionRepo.count() + 1;
        return String.format("SS%03d", count); // e.g., SS001
    }



}