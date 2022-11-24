package com.euniversity.manager.model.viewobj.rowmapper;

import com.euniversity.manager.model.entity.Course;
import com.euniversity.manager.model.viewobj.CourseInfo;
import com.euniversity.manager.model.viewobj.FullGrade;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseInfoRM implements RowMapper<CourseInfo> {

    @Override
    public CourseInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        CourseInfo ci = new CourseInfo();
        ci.setCourseid(resultSet.getInt(1));
        ci.setCoursename(resultSet.getString(2));
        ci.setTeacherFname(resultSet.getString(3));
        ci.setTeacherLname(resultSet.getString(4));
        return ci;
    }
}
