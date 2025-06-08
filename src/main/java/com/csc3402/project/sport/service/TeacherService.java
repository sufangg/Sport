package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.Teacher;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Optional<Teacher> findTeacherById(String id);
    List<Teacher> listAllTeachers();
    Teacher addNewTeacher(Teacher teacher);
    Teacher updateTeacher(String teacherId, Teacher teacher);
    void deleteTeacher(Teacher teacher);
    Teacher findTeacherByUsername(String username);
    void saveTeacher(Teacher teacher);
}