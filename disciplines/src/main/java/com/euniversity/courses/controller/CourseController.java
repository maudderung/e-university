package com.euniversity.courses.controller;

import com.euniversity.courses.model.Course;
import com.euniversity.courses.repository.CourseRepository;
import com.euniversity.courses.repository.routines.CoursesStudentsCount;
import com.euniversity.courses.service.CourseService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CoursesStudentsCount coursesStudentsCount;

    @Autowired
    private CourseService courseService;

    @CrossOrigin
    @GetMapping("/page/{page}")
    public @ResponseBody ResponseEntity<List<Course>> getAllCourses(@PathVariable("page") int page) {
        Page<Course> pageList=courseRepository.findAll(PageRequest.of(page-1,50));
        List<Course> coursesList=pageList.getContent();
        return ResponseEntity.ok(coursesList);
    }

    @CrossOrigin
    @GetMapping("/{name}/page/{page}")
    public @ResponseBody ResponseEntity<List<Course>> getAllCoursesByNamePage(@PathVariable("page") int page,@PathVariable("name") String name) {
        Page<Course> pageList=courseRepository.findCoursesByNameContainsIgnoreCaseOrderByCourseid(name,PageRequest.of(page-1,50));
        List<Course> coursesList=pageList.getContent();
        return ResponseEntity.ok(coursesList);
    }

    @CrossOrigin
    @GetMapping("/id/{course_id}")
    public ResponseEntity<Course> getCourse(@PathVariable("course_id") long course_id) {
        Course course = courseRepository.findById(course_id).orElseThrow(() -> new NoSuchElementException("No such product id: "+course_id));
        return ResponseEntity.ok(course);
    }

    @CrossOrigin
    @GetMapping("/count/{course_id}")
    public ResponseEntity<Integer> getCourseStudentsCount(@PathVariable("course_id") int course_id) {
        return ResponseEntity.ok(coursesStudentsCount.execute(course_id));
    }

    @CrossOrigin
    @GetMapping("pagecount")
    public ResponseEntity<List<Integer>> getCoursePagesAll() {
        return ResponseEntity.ok(courseService.getAllPages());
    }

    @CrossOrigin
    @GetMapping("/pagecount/{name}")
    public ResponseEntity<List<Integer>> getCoursePagesName(@PathVariable("name") String name) {
        return ResponseEntity.ok(courseService.getNamePages(name));
    }

    @CrossOrigin
    @PostMapping("/delete")
    public @ResponseBody ResponseEntity<Boolean> deleteCourse(@RequestBody String courseId) {
       try{
            courseRepository.deleteById(Long.parseLong(courseId));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
        return ResponseEntity.ok(true);
    }
}
