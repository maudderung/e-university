package com.euniversity.teacher.repository.routines;


import com.euniversity.teacher.model.Teacher;
import com.euniversity.teacher.model.rowmapper.TeacherRM;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class TeacherByCourseID {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Teacher> execute(int courseid) {
        String sql = "select t.* from teacher t join courses_teachers_info cti on t.teacher_id=cti.teacher_id where cti.course_id="+ courseid + " order by 1";

        List<Teacher> teacher = jdbcTemplate.query(sql,new TeacherRM());
        return teacher;
    }

}
