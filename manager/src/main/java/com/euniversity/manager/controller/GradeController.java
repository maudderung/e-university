package com.euniversity.manager.controller;

import com.euniversity.manager.model.entity.Student;
import com.euniversity.manager.model.viewobj.FullGrade;
import com.euniversity.manager.model.viewobj.dto.GradeDTO;
import com.euniversity.manager.model.viewobj.ParamsObj;

import com.euniversity.manager.repository.EnrolledStudentsRepository;
import com.euniversity.manager.repository.GradesRepository;
import com.euniversity.manager.service.EuniService;
import com.euniversity.manager.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/euni")
public class GradeController {
    private final EuniService euniService;

    private final GradeService gradeService;
    private final EnrolledStudentsRepository enrolledStudentsRepository;
    private final GradesRepository gradesRepository;


    public GradeController(GradesRepository gradesRepository, EnrolledStudentsRepository enrolledStudentsRepository, EuniService euniService, GradeService gradeService) {
        this.gradesRepository = gradesRepository;
        this.enrolledStudentsRepository = enrolledStudentsRepository;
        this.euniService = euniService;
        this.gradeService = gradeService;
    }

    @GetMapping("/grades/id/{grade_id}")
    public @ResponseBody ResponseEntity<FullGrade> getGrade(@PathVariable("grade_id") long grade_id){
        return ResponseEntity.ok(gradeService.getGrade(grade_id));
    }

    @GetMapping("/grades")
    public ModelAndView allGrades(@RequestParam(value="name",required=false) String name,
                                  @RequestParam(value="page",required = false) Integer page){
        List<FullGrade> gradesList;
        List<Integer> pages;

        if(page==null)
            page=1;

        if(name==null || name.isEmpty()) {
            gradesList = gradeService.getAllGrades(page - 1);
            pages = gradeService.getGradePages();

        }else{
            gradesList=gradeService.getGradesOfStudentsByName(name,page);
            pages=gradeService.getStudentNameGradePages(name);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("grade");


        modelAndView.addObject("gradesList", gradesList);
        modelAndView.addObject("pages",pages);
        modelAndView.addObject("currentPage",page);
        modelAndView.addObject("totalPages",pages.get(pages.size()-1));

        modelAndView.addObject("paramsObj",new ParamsObj(name,page));

        return modelAndView;
    }

    @PostMapping("/grades/edit")
    public ModelAndView editGrade(@ModelAttribute("grade") FullGrade grade,
                                  @RequestParam(value="updated",required=false) boolean updatedStatus){
        if(grade.isForUpdate()){
            gradeService.updateGrade(grade.getGrade_id(),grade.getGrade());
            updatedStatus=true;
        }
        grade.setForUpdate(true);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("updatedStatus",updatedStatus);
        modelAndView.addObject("grade",grade);
        modelAndView.setViewName("editgrade");

        return modelAndView;
    }

    @PostMapping("/grades/student")
    public ModelAndView gradeByCourseForStudent(@ModelAttribute("student") Student student,
                                                @RequestParam(value="page",required = false) Integer page){
        List<GradeDTO> gradesList;
        List<Integer> pages;

        if(page==null)
            page=1;

        gradesList=gradeService.getAllGradesOfStudent(student.getFacNum(),page);
        pages=gradeService.getAllGradesOfStudentPages(student.getFacNum());


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gradecourse");


        modelAndView.addObject("gradesList", gradesList);
        modelAndView.addObject("pages",pages);
        modelAndView.addObject("currentPage",page);
        modelAndView.addObject("totalPages",pages.get(pages.size()-1));

        return modelAndView;
    }


    @GetMapping("/")
    public ModelAndView indexPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }


}
