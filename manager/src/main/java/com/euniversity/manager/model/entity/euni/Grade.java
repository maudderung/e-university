package com.euniversity.manager.model.entity.euni;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gradeid")
    long gradeid;

    int studentfacnum;

    private int teacherid;

    private int courseid;

    LocalDate date;

    int grade;

    @Override
    public String toString() {
        return "Grade{" +
                "grade_id=" + gradeid +
                ", student_fac_num=" + studentfacnum +
                ", teacher_id=" + teacherid +
                ", course_id=" + courseid +
                ", date=" + date +
                ", grade=" + grade +
                '}';
    }
}
