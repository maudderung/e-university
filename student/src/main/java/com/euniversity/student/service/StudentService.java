package com.euniversity.student.service;

import com.euniversity.student.model.Student;
import com.euniversity.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> students = studentRepository.findStudentsByFirstNameContainsIgnoreCase(name);
        List<Student> lastNameStudents = studentRepository.findStudentsByLastNameContainsIgnoreCase(name);
        students.addAll(lastNameStudents);

        List<Student> studentsNoDupes=students.stream().distinct().collect(Collectors.toList());
        students.sort(Comparator.comparingLong(Student::getFacNum));

        return studentsNoDupes;
    }

    public void updateStudent(Student student) {
        Student studentForUpdate = studentRepository.findByFacNumEquals(student.getFacNum());
        studentForUpdate.setFirstName(student.getFirstName());
        studentForUpdate.setLastName(student.getLastName());
        studentForUpdate.setGradYear(student.getGradYear());

        studentRepository.save(studentForUpdate);
    }
}
