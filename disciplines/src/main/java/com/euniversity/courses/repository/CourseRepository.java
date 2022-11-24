package com.euniversity.courses.repository;

import com.euniversity.courses.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Page<Course> findAll(Pageable pageable);
    Page<Course> findCoursesByNameContainsIgnoreCaseOrderByCourseid(String name, Pageable pageable);

    int countByNameContainsIgnoreCase(String name);

}
