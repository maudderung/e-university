package com.euniversity.manager.service;

import com.euniversity.manager.model.entity.Course;
import com.euniversity.manager.model.viewobj.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    EuniService euniService;
    public List<CourseDTO> getAllCourses(int page) {
        List<CourseDTO> dtoList;

        List<Course> coursesList=euniService.getCoursesFromPageRequest(page);
        dtoList=mapCourseToDTO(coursesList);

        return dtoList;
    }
    public List<CourseDTO> getCoursesByName(String name,int page) {
        List<CourseDTO> dtoList;
        List<Course> coursesList=new ArrayList<>();

        if(name.isEmpty()){
            coursesList= euniService.getCoursesFromPageRequest(page);
        }else {
            coursesList = euniService.getCoursesByNamePageRequest(name, page);
        }

        dtoList=mapCourseToDTO(coursesList);

        return dtoList;
    }

    public boolean deleteCourse(int studentId){
        return euniService.sendDeleteCourseRequest(studentId);
    }

    public List<Integer> getCoursesPageAll(){
        return euniService.getCoursesPagesAllRequest();
    }

    public List<Integer> getCoursesPageName(String name){
        return euniService.getCoursesPagesNameRequest(name);
    }

    private List<CourseDTO> mapCourseToDTO(List<Course> coursesList) {
        List<CourseDTO> dtoList=new ArrayList<>();
        CourseDTO courseDTO;

        for(Course course: coursesList){
            courseDTO=new CourseDTO();

            //List<Teacher> teachers=euniService.getTeacherByCourseIDFromRequest((int)course.getCourse_id());
            int enrolled=euniService.getCoursesStudentsCountRequest((int)course.getCourseid());
            courseDTO.setCourseid(course.getCourseid());
            courseDTO.setName(course.getName());
            courseDTO.setMaxcapacity(course.getMaxcapacity());
            courseDTO.setTotalEnrolled(enrolled);

            dtoList.add(courseDTO);
        }

        return dtoList;
    }
}
