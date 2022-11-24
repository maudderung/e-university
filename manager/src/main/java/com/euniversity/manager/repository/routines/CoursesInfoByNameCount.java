package com.euniversity.manager.repository.routines;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CoursesInfoByNameCount {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int execute(long facnum) {
        String sql = "select * from coursesinfobynamecount("+facnum+")";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }
}
