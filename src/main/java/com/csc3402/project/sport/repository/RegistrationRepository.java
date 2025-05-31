package com.csc3402.project.sport.repository;

import com.csc3402.project.sport.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId>{
    List<Registration> findByStudent(Student student);
    List<Registration> findBySportSession_SessionId(String sessionId);
}
