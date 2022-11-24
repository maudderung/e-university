package com.euniversity.student.repository;

import com.euniversity.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByFirstNameContainsOrLastNameContains(String firstname, String lastname);
    List<Student> findStudentsByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(String firstname, String lastname);
    List<Student> findStudentsByFirstNameLikeIgnoreCaseAndLastNameLikeIgnoreCase(String firstname, String lastname);

    Page<Student> findAllByOrderByFacNum(Pageable pageable);
    List<Student> findStudentsByFirstNameContainsIgnoreCase(String firstname);

    List<Student> findStudentsByLastNameContainsIgnoreCase(String firstname);

    Student findByFacNumEquals(long facnum);



}
