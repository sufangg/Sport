package com.csc3402.project.sport.repository;

import com.csc3402.project.sport.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportSessionRepository extends JpaRepository<SportSession, String> {

    @Query("""
    SELECT s FROM SportSession s
    WHERE s.sport.sportName LIKE %:keyword%
       OR s.sessionId LIKE %:keyword%
       OR s.sport.sportId LIKE %:keyword%
    """)
    List<SportSession> searchBySportOrSessionId(@Param("keyword") String keyword);
    List<SportSession> findByTeacher_TeacherId(String teacherId);
    List<SportSession> findByTeacherEmail(String email);
    List<SportSession> findByTeacher(Teacher teacher);
}

