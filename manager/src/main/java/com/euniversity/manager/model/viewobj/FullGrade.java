package com.euniversity.manager.model.viewobj;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FullGrade implements Serializable {

    long grade_id;
    String student_firstName;
    String student_lastName;
    int student_fac_num;
    String courseName;
    String teacher_firstName;
    String teacher_lastName;
    int grade;
    String date;
    boolean forUpdate;

    @Override
    public String toString() {
        return "FullGrade{" +
                "grade_id=" + grade_id +
                ", student_firstName='" + student_firstName + '\'' +
                ", student_lastName='" + student_lastName + '\'' +
                ", student_fac_num=" + student_fac_num +
                ", courseName='" + courseName + '\'' +
                ", teacher_firstName='" + teacher_firstName + '\'' +
                ", teacher_lastName='" + teacher_lastName + '\'' +
                ", grade=" + grade +
                ", date='" + date + '\'' +
                ", forUpdate=" + forUpdate +
                '}';
    }
}
