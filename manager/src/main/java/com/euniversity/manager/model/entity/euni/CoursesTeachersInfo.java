package com.euniversity.manager.model.entity.euni;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="courses_teachers_info")
public class CoursesTeachersInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    long teacherid;

    int courseid;

    LocalDate teacherstartdate;

    LocalDate teacherenddate;
}
