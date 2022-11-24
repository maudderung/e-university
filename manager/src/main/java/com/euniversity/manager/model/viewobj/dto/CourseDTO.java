package com.euniversity.manager.model.viewobj.dto;

import com.euniversity.manager.model.entity.Course;
import com.euniversity.manager.model.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO extends Course {
    List<Teacher> teachers;
    int totalEnrolled;

}
