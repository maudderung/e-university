package com.euniversity.manager.controller;

import com.euniversity.manager.model.viewobj.dto.CourseDTO;
import com.euniversity.manager.model.viewobj.ParamsObj;
import com.euniversity.manager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("euni")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/courses")
    public ModelAndView allCourses(@RequestParam(value="name",required=false) String name,
                                   @RequestParam(value="page",required = false) Integer page,
                                   @RequestParam(value="deleted",required = false) boolean deleted,
                                   @RequestParam(value="actionDelete",required = false) boolean actionDelete){
        List<CourseDTO> coursesList;
        List<Integer> pages;

        if(page==null)
            page=1;

        if(name==null) {
            coursesList = courseService.getAllCourses(page);
            pages = courseService.getCoursesPageAll();

        }else{
            coursesList=courseService.getCoursesByName(name,page);
            pages=courseService.getCoursesPageName(name);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("course");

        modelAndView.addObject("coursesList", coursesList);
        modelAndView.addObject("pages",pages);
        modelAndView.addObject("currentPage",page);
        modelAndView.addObject("totalPages",pages.get(pages.size()-1));

        modelAndView.addObject("actionDelete",actionDelete);
        modelAndView.addObject("deleted",deleted);
        modelAndView.addObject("paramsObj",new ParamsObj(name,page));

        return modelAndView;
    }

    @PostMapping("/courses/delete")
    public ModelAndView deleteCourse(@RequestParam(value="courseid") long courseid){
        List<CourseDTO> coursesList;
        List<Integer> pages;

        boolean deleted=courseService.deleteCourse((int)courseid);

        ModelAndView modelAndView =  new ModelAndView("redirect:/euni/courses");
        modelAndView.addObject("actionDelete",true);
        modelAndView.addObject("deleted",deleted);

        return modelAndView;
    }



}
