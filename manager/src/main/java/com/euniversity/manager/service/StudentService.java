package com.euniversity.manager.service;

import com.euniversity.manager.model.entity.Course;

import com.euniversity.manager.model.entity.euni.EnrolledStudents;
import com.euniversity.manager.model.entity.Student;

import com.euniversity.manager.repository.EnrolledStudentsRepository;
import com.euniversity.manager.repository.routines.CoursesInfoByNameCount;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {


    private final Environment environment;

    @Autowired
    private final EnrolledStudentsRepository enrolledStudentsRepository;

    @Autowired
    private CoursesInfoByNameCount coursesInfoByNameCount;
    private final EuniService euniService;

    OkHttpClient client = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public StudentService(Environment environment, EnrolledStudentsRepository enrolledStudentsRepository, EuniService euniService) {
        this.environment = environment;
        this.enrolledStudentsRepository = enrolledStudentsRepository;

        this.euniService = euniService;
    }


    public Student getStudent(long facNum) {
        Student student = euniService.getStudentFromRequest(Long.toString(facNum));
        return student;
    }

    public List<Student> getStudentsByName(String name, Integer page) {
        return euniService.getStudentsByNameFromRequest(name, page);
    }

    public List<Student> getAllStudents(int page) {
        return euniService.getStudentsPageFromRequest(page);
    }

    public List<Course> getAllCoursesEnrolledByStudent(long facNum, int page) {
        Pageable pageable = PageRequest.of(page, 50);

        //Get all courses of said student
        List<EnrolledStudents> coursesIdByStudent = enrolledStudentsRepository.findAllByStudentfacnumEquals(facNum);
        List<Course> courses = new ArrayList<>();

        //Get name of course
        for (EnrolledStudents course : coursesIdByStudent) {
            Course foundCourseOfStudent = euniService.getCourseFromRequest(String.valueOf(course.getCourseid()));
            courses.add(foundCourseOfStudent);
        }
        List<Course> pagedCourse = courses.subList((page - 1) * 50, page * 50);

        return pagedCourse;
    }


    public List<Integer> getStudentsPages() {
        return euniService.getStudentPagesCount();
    }

    public List<Integer> getStudentsNamePages(String name) {
        return euniService.getStudentNamePagesCount(name);
    }

    public List<Integer> getStudentsCoursesPages(long facnum) {
        int count = coursesInfoByNameCount.execute(facnum);
        return euniService.calculatePages(count);
    }


    public Student updateStudent(Student student) {
        return euniService.sendUpdateStudentRequest(student);
    }

}


