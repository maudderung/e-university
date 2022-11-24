package com.euniversity.teacher.service;


import com.euniversity.teacher.model.Teacher;
import com.euniversity.teacher.repository.routines.TeacherByCourseID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherByCourseID teacherByCourseID;

    public List<Teacher> getTeachersByCourseId(int courseId){
        return teacherByCourseID.execute(courseId);
    }
}
