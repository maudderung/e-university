package com.euniversity.manager.model.viewobj.rowmapper;

import com.euniversity.manager.model.viewobj.FullGrade;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FullGradeRM implements RowMapper<FullGrade> {

    @Override
    public FullGrade mapRow(ResultSet resultSet, int i) throws SQLException {
        FullGrade fg = new FullGrade();
        fg.setGrade_id(resultSet.getLong(1));
        fg.setStudent_firstName(resultSet.getString(2));
        fg.setStudent_lastName(resultSet.getString(3));
        fg.setStudent_fac_num(resultSet.getInt(4));
        fg.setCourseName(resultSet.getString(5));
        fg.setTeacher_firstName(resultSet.getString(6));
        fg.setTeacher_lastName(resultSet.getString(7));
        fg.setGrade(resultSet.getInt(8));
        fg.setDate(resultSet.getDate(9).toLocalDate().toString());
        return fg;
    }
}
