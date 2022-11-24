package com.euniversity.manager.model.viewobj.rowmapper;

import com.euniversity.manager.model.viewobj.dto.GradeDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeDTORM implements RowMapper<GradeDTO> {

    @Override
    public GradeDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        GradeDTO gr = new GradeDTO();
        gr.setCoursename(resultSet.getString(1));
        gr.setGrade(resultSet.getInt(2));
        gr.setDate(resultSet.getDate(3).toLocalDate().toString());
        gr.setTeacherFname(resultSet.getString(4));
        gr.setTeacherLname(resultSet.getString(5));
        return gr;
    }
}
