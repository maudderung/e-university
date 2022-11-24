package com.euniversity.manager.service;

import com.euniversity.manager.model.entity.Course;
import com.euniversity.manager.model.entity.euni.Grade;
import com.euniversity.manager.model.entity.Student;
import com.euniversity.manager.model.entity.Teacher;
import com.euniversity.manager.model.viewobj.FullGrade;
import com.euniversity.manager.model.viewobj.dto.GradeDTO;
import com.euniversity.manager.repository.GradesRepository;
import com.euniversity.manager.repository.routines.FullGradesByNameCount;
import com.euniversity.manager.repository.routines.FullGradesByStudentName;
import com.euniversity.manager.repository.routines.StudentAllGrades;
import com.euniversity.manager.repository.routines.StudentAllGradesCount;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//TODO: ServiceImpl
@Service
public class GradeService {


    @Autowired
    private Environment environment;
    @Autowired
    private GradesRepository gradesRepository;
    @Autowired
    private FullGradesByStudentName fullGradesByStudentName;
    @Autowired
    private FullGradesByNameCount fullGradesByNameCount;
    @Autowired
    private StudentAllGrades studentAllGrades;
    @Autowired
    private StudentAllGradesCount studentAllGradesCount;
    @Autowired
    private EuniService euniService;

    OkHttpClient client = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();


    public FullGrade getGrade(long grade_id) {
        Grade grade = gradesRepository.findById(grade_id).orElseThrow(() -> new NoSuchElementException("No grade with id " + grade_id));
        return mapGrade(grade);
    }

    public List<FullGrade> getAllGrades(int page) {
        Page<Grade> gradesList = gradesRepository.findAllByOrderByGradeid(PageRequest.of(page, 50));
        List<FullGrade> fullGradesList = new ArrayList<>();

        for (Grade grade : gradesList) {
            FullGrade fullGrade = mapGrade(grade);
            fullGradesList.add(fullGrade);
        }
        return fullGradesList.stream().toList();
    }

    public List<FullGrade> getAllGradesByStudent(long facNum,int page) {
        Pageable pageable = PageRequest.of(page, 50);
        Page<Grade> gradesList =  gradesRepository.findGradesByStudentfacnumEquals((int)facNum,pageable);
        List<FullGrade> fullGradesList = new ArrayList<>();

        for (Grade grade : gradesList) {
            FullGrade fullGrade = mapGrade(grade);
            fullGradesList.add(fullGrade);
        }
        return fullGradesList.stream().toList();
    }

    public List<FullGrade> getGradesOfStudentsByName(String name,int page) {
        return fullGradesByStudentName.execute(name,page);
    }
    public List<Integer> getStudentNameGradePages(String name){
        int count=fullGradesByNameCount.execute(name);
        return euniService.calculatePages(count);
    }
    public List<Integer> getGradePages(){
        long count = gradesRepository.count();
        return euniService.calculatePages((int)count);
    }

    public List<GradeDTO> getAllGradesOfStudent(long facnum, int page){
        return studentAllGrades.execute(facnum,page);
    }
    public List<Integer> getAllGradesOfStudentPages(long facnum){
        int count=studentAllGradesCount.execute(facnum);
        return euniService.calculatePages(count);
    }



    private FullGrade mapGrade(Grade grade) {
        int courseId = grade.getCourseid();
        int teacherId = grade.getTeacherid();
        int facNum = grade.getStudentfacnum();

        Student student = euniService.getStudentFromRequest(Integer.toString(facNum));
        Teacher teacher = euniService.getTeacherFromRequest(Integer.toString(teacherId));
        Course course = euniService.getCourseFromRequest(Integer.toString(courseId));

        FullGrade fullGrade = new FullGrade();

        fullGrade.setGrade_id(grade.getGradeid());
        fullGrade.setGrade(grade.getGrade());

        fullGrade.setStudent_firstName(student.getFirstName());
        fullGrade.setStudent_lastName(student.getLastName());
        fullGrade.setStudent_fac_num((int) student.getFacNum());

        fullGrade.setTeacher_firstName(teacher.getFirstname());
        fullGrade.setTeacher_lastName(teacher.getLastname());

        fullGrade.setCourseName(course.getName());
        fullGrade.setDate((grade.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

        return fullGrade;
    }

    public void updateGrade(long id,int grade){
        Grade gradeForUpdate = gradesRepository.findGradeByGradeidEquals(id);
        gradeForUpdate.setGrade(grade);
        gradesRepository.save(gradeForUpdate);
    }



}
