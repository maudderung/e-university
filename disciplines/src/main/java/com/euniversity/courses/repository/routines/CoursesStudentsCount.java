package com.euniversity.courses.repository.routines;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CoursesStudentsCount {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int execute(int course_id) {
        String sql = "select count(*) from enrolled_students where courseid="+course_id;
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

}
