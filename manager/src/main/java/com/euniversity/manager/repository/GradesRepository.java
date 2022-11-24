package com.euniversity.manager.repository;

import com.euniversity.manager.model.entity.euni.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradesRepository extends JpaRepository<Grade, Long> {

    Page<Grade> findAllByOrderByGradeid(Pageable pageable);

    Page<Grade> findGradesByStudentfacnumEquals(int facnum, Pageable pageable);

    Grade findGradeByGradeidEquals(long gradeid);


}
