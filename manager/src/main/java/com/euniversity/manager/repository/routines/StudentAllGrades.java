package com.euniversity.manager.repository.routines;

import com.euniversity.manager.model.viewobj.dto.GradeDTO;
import com.euniversity.manager.model.viewobj.rowmapper.GradeDTORM;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@NoArgsConstructor
public class StudentAllGrades {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<GradeDTO> execute(long facnum, int page) {

        String sql = "studentAllGrades(" + facnum + "," + page + ")";

        List<GradeDTO> grades =
                jdbcTemplate.query("select *  from "+sql
                        ,new GradeDTORM());
        return grades;
    }

}
