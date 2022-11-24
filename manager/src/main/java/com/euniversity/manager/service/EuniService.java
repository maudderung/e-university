package com.euniversity.manager.service;

import com.euniversity.manager.model.entity.Course;
import com.euniversity.manager.model.entity.Student;
import com.euniversity.manager.model.entity.Teacher;
import com.euniversity.manager.repository.GradesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO: ServiceImpl
@Service
public class EuniService {

    @Autowired
    private Environment environment;
    @Autowired
    private GradesRepository gradesRepository;

    OkHttpClient client = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public Teacher getTeacherFromRequest(String teacherId) {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.teacher") + teacherId)
                //.url("http://localhost:8089/teacher/"+ teacherId)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Teacher> getTeacherByCourseIDFromRequest(int course_id) {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.teacher")+"course/"+course_id)
                //.url("http://localhost:8089/teacher/"+ teacherId)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Student getStudentFromRequest(String facNum) {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.student") +"id/"+ facNum)
                //.url(environment.getProperty("address.student") +"id/"+ facNum)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getStudentsPageFromRequest(int page) {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.student") +"page/"+ page)
                //.url(environment.getProperty("address.student") +"id/"+ facNum)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getStudentPagesCount() {

        Request request = new Request.Builder()
                .url(environment.getProperty("address.student") +"pages/")
                //.url("http://localhost:8087/student/name/"+ name)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Integer> getStudentNamePagesCount(String name) {

        Request request = new Request.Builder()
                .url(environment.getProperty("address.student") +name+"/pages/")
                //.url("http://localhost:8087/student/name/"+ name)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getStudentsByNameFromRequest(String name,Integer page) {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.student") + name+"/page/"+page)
                //.url("http://localhost:8087/student/name/"+ name)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            List<Student> students = mapper.readValue(response.body().string(), new TypeReference<>(){});
            return students;
        } catch (IOException e) {
            return null;
        }
    }

    public Course getCourseFromRequest(String courseId) {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.course") + "id/"+courseId)
                //.url("http://localhost:8088/course/id/"+ courseId)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Course> getCoursesFromPageRequest(int page) {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.course")+"page/"+page)
                //.url("http://localhost:8088/course/id/"+ courseId)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Course> getCoursesByNamePageRequest(String name,int page) {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.course")+name+"/page/"+page)
                //.url("http://localhost:8088/course/id/"+ courseId)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Integer getCoursesStudentsCountRequest(int course_id) {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.course")+"count/"+course_id)
                //.url("http://localhost:8088/course/id/"+ courseId)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getCoursesPagesAllRequest() {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.course")+"pagecount")
                //.url("http://localhost:8088/course/id/"+ courseId)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getCoursesPagesNameRequest(String name) {
        Request request = new Request.Builder()
                .url(environment.getProperty("address.course")+"pagecount/"+name)
                //.url("http://localhost:8088/course/id/"+ courseId)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Student sendUpdateStudentRequest(Student student) {

        String studentJson = null;
        try {
            studentJson = new ObjectMapper().writeValueAsString(student);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        RequestBody body = RequestBody.create(JSON,studentJson);


        Request request = new Request.Builder()
                .url(environment.getProperty("address.student") + "update")
                //.url("http://localhost:8088/course/id/"+ courseId)
                .post(body)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendDeleteCourseRequest(int studentId) {
        RequestBody body = RequestBody.create(JSON, String.valueOf(studentId));

        Request request = new Request.Builder()
                .url(environment.getProperty("address.course") + "delete")
                //.url("http://localhost:8088/course/id/"+ courseId)
                .post(body)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return mapper.readValue(response.body().string(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> calculatePages(int count) {
        List<Integer> pages=new ArrayList<>();
        for(int i=1; i<=count/50;i++){
            pages.add(i);
        }
        if(count%50!=0){
            pages.add(pages.size()+1);
        }else if(count<50){
            pages.add(1);
        }
        return pages;
    }
}
