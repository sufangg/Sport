package com.csc3402.project.sport.repository;

import com.csc3402.project.sport.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    Student findByEmail(String email);
}
