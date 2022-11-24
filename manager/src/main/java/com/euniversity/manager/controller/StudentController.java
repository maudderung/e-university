package com.euniversity.manager.controller;

import com.euniversity.manager.model.entity.Student;
import com.euniversity.manager.model.viewobj.CourseInfo;
import com.euniversity.manager.model.viewobj.FullGrade;
import com.euniversity.manager.model.viewobj.ParamsObj;

import com.euniversity.manager.repository.EnrolledStudentsRepository;
import com.euniversity.manager.repository.GradesRepository;
import com.euniversity.manager.repository.routines.CoursesInfoByStudent;
import com.euniversity.manager.service.EuniService;
import com.euniversity.manager.service.GradeService;
import com.euniversity.manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/euni")
public class StudentController {
    private final EuniService euniService;

    @Autowired
    StudentService studentService;

    @Autowired
    CoursesInfoByStudent coursesInfoByStudent;
    private final GradeService gradeService;
    private final EnrolledStudentsRepository enrolledStudentsRepository;
    private final GradesRepository gradesRepository;


    public StudentController(GradesRepository gradesRepository,EnrolledStudentsRepository enrolledStudentsRepository, EuniService euniService, GradeService gradeService) {
        this.gradesRepository = gradesRepository;
        this.enrolledStudentsRepository = enrolledStudentsRepository;
        this.euniService = euniService;
        this.gradeService = gradeService;
    }


    @GetMapping("/students")
    public ModelAndView allStudents(@RequestParam(value="name",required=false) String name,
                                    @RequestParam(value="page",required = false) Integer page){
        List<Student> studentsList;
        List<Integer> pages;
        boolean forUpdate=false;

        if(page==null)
            page=1;

        if(name==null || name.isEmpty()) {
            studentsList=studentService.getAllStudents(page);
            pages = studentService.getStudentsPages();

        }else{
            studentsList=studentService.getStudentsByName(name,page);
            pages = studentService.getStudentsNamePages(name);
        }

        if(studentsList==null){
            studentsList=new ArrayList<>();
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");

        modelAndView.addObject("studentsList", studentsList);
        modelAndView.addObject("forUpdate", forUpdate);
        modelAndView.addObject("pages",pages);
        modelAndView.addObject("currentPage",page);
        modelAndView.addObject("totalPages",pages.get(pages.size()-1));

        modelAndView.addObject("paramsObj",new ParamsObj(name,page));

        return modelAndView;
    }


    @PostMapping("/students/edit")
    public ModelAndView editStudent(@ModelAttribute("student") Student student,
                                    @RequestParam("forUpdate") boolean forUpdate,
                                    @RequestParam(value="updated",required=false) boolean updatedStatus){
        if(forUpdate){
            studentService.updateStudent(student);
            updatedStatus=true;
        }
        forUpdate=true;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editstudent");
        modelAndView.addObject("updatedStatus",updatedStatus);
        modelAndView.addObject("student", student);
        modelAndView.addObject("forUpdate", forUpdate);


        return modelAndView;
    }

    @GetMapping("/students/info")
    public ModelAndView viewStudentInfo(@ModelAttribute("student") Student student,
                                        @RequestParam(value="page",required = false) Integer page) {
        List<CourseInfo> coursesList;
        List<Integer> pages;

        if(page==null)
            page=1;

        coursesList=coursesInfoByStudent.execute(student.getFacNum(),page);
        pages = studentService.getStudentsCoursesPages(student.getFacNum());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentinfo");
        modelAndView.addObject("student", student);
        modelAndView.addObject("coursesList", coursesList);
        modelAndView.addObject("pages",pages);
        modelAndView.addObject("currentPage",page);
        modelAndView.addObject("totalPages",pages.get(pages.size()-1));

        return modelAndView;
    }

}
