package com.euniversity.manager.repository.routines;

import com.euniversity.manager.model.viewobj.CourseInfo;
import com.euniversity.manager.model.viewobj.FullGrade;
import com.euniversity.manager.model.viewobj.rowmapper.CourseInfoRM;
import com.euniversity.manager.model.viewobj.rowmapper.FullGradeRM;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class CoursesInfoByStudent {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CourseInfo> execute(long facnum, int page) {
        String sql = "coursesinfobystudent("+ facnum + "," + page + ")";

        List<CourseInfo> coursesOfStudent = jdbcTemplate.query("select * from "+sql,new CourseInfoRM());
        return coursesOfStudent;
    }

}
