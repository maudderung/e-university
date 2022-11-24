package com.euniversity.teacher.controller;

import com.euniversity.teacher.model.Teacher;
import com.euniversity.teacher.repository.TeacherRepository;
import com.euniversity.teacher.service.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/teacher")

public class TeacherController {
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    TeacherService teacherService;
    @Autowired
    private TeacherRepository teacherRepository;

    @CrossOrigin
    @GetMapping("/{teacher_id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable("teacher_id") long teacher_id){
        Teacher teacher=teacherRepository.findById(teacher_id).orElseThrow(()-> new NoSuchElementException("No teacher with id "+teacher_id));
        return ResponseEntity.ok(teacher);
    }

    @CrossOrigin
    @GetMapping("/course/{course}")
    public ResponseEntity<List<Teacher>> getTeacherByCourseID(@PathVariable("course") int course_id){
        return ResponseEntity.ok(teacherService.getTeachersByCourseId(course_id));
    }

}
