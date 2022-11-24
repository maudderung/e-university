package com.euniversity.manager.model.viewobj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CourseInfo {
    int courseid;
    String coursename;
    String teacherFname;
    String teacherLname;

}
