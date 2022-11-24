package com.euniversity.manager.model.viewobj.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class GradeDTO {
    String coursename;
    int grade;
    String date;
    String teacherFname;
    String teacherLname;
}
