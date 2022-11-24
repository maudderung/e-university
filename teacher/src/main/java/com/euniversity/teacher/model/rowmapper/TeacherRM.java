package com.euniversity.teacher.model.rowmapper;

import com.euniversity.teacher.model.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRM implements RowMapper<Teacher> {

    @Override
    public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
        Teacher t = new Teacher();
        t.setTeacherid(resultSet.getInt(1));
        t.setFirstname(resultSet.getString(2));
        t.setLastname(resultSet.getString(3));
        t.setDegree(resultSet.getString(4));
        return t;
    }
}
