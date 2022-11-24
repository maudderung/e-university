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
@Table(name="enrolled_students")
public class EnrolledStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="studentfacnum")
    long studentfacnum;

    @Column(name="courseid")
    int courseid;

    @Column(name="coursestartdate")
    LocalDate coursestartdate;

    @Column(name="courseenddate")
    LocalDate courseenddate;
}
