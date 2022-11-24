package com.euniversity.courses.service;

import com.euniversity.courses.model.Course;
import com.euniversity.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> getCoursesByName(String name,int page){
        return courseRepository.findCoursesByNameContainsIgnoreCaseOrderByCourseid(name, PageRequest.of(page-1,50)).getContent();
    }

    public List<Integer> getAllPages(){
        return calculatePages(courseRepository.count());
    }

    public List<Integer> getNamePages(String name){
        int count=courseRepository.countByNameContainsIgnoreCase(name);
        return calculatePages(count);
    }

    public List<Integer> calculatePages(long count) {
        List<Integer> pages=new ArrayList<>();
        for(int i=1; i<=count/50;i++){
            pages.add(i);
        }
        if(count%50!=0){
            pages.add(pages.size()+1);
        }else if(count<50){
            pages.add(1);
        }
        return pages;
    }
}
