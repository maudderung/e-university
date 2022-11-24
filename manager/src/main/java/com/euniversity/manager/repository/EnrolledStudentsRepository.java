package com.euniversity.manager.repository;


import com.euniversity.manager.model.entity.euni.EnrolledStudents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrolledStudentsRepository extends JpaRepository<EnrolledStudents,Long> {
    List<EnrolledStudents> findAllByStudentfacnumEquals(long studentfacnum);

}
