package com.euniversity.manager.repository.routines;

import com.euniversity.manager.model.viewobj.FullGrade;
import com.euniversity.manager.model.viewobj.rowmapper.FullGradeRM;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;


import java.util.List;

@NoArgsConstructor
@Component
public class FullGradesByStudentName {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<FullGrade> execute(String studentName, int page) {

        String sql = "fullGradeByStudentName('" + studentName + "'," + page + ")";

        List<FullGrade> fullGrades =
                jdbcTemplate.query("select gradeid ,StudentFname, studentLname ,facnum ,courseName, teacherFname , teacherLname ,grade , date  from "+sql
                ,new FullGradeRM());
        return fullGrades;
    }


}
