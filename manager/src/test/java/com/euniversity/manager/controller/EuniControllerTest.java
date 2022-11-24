package com.euniversity.manager.controller;

import com.euniversity.manager.model.viewobj.FullGrade;
import com.euniversity.manager.model.entity.Course;
import com.euniversity.manager.model.entity.euni.Grade;
import com.euniversity.manager.model.entity.Student;
import com.euniversity.manager.model.entity.Teacher;
import com.euniversity.manager.service.EuniService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class EuniControllerTest {
    EuniService euniService=new EuniService();

    @Test
    void getGrade() {
        LocalDate date = LocalDate.of(1991,1,16);

        Grade grade= new Grade(340L,13,36,332,date ,4);

        int courseId=grade.getCourseid();
        int teacherId=grade.getTeacherid();
        int facNum=grade.getStudentfacnum();

        Student student=euniService.getStudentFromRequest(Integer.toString(facNum));
        Teacher teacher=euniService.getTeacherFromRequest(Integer.toString(teacherId));
        Course course=euniService.getCourseFromRequest(Integer.toString(courseId));

        FullGrade fullGrade= new FullGrade();

        fullGrade.setGrade_id(grade.getGradeid());
        fullGrade.setGrade(grade.getGrade());

        fullGrade.setStudent_firstName(student.getFirstName());
        fullGrade.setStudent_lastName(student.getLastName());
        fullGrade.setStudent_fac_num((int) student.getFacNum());

        fullGrade.setTeacher_firstName(teacher.getFirstname());
        fullGrade.setTeacher_lastName(teacher.getLastname());

        fullGrade.setCourseName(course.getName());
        fullGrade.setDate(grade.getDate());

    }


}