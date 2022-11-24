package com.euniversity.student.controller;

import com.euniversity.student.model.Student;
import com.euniversity.student.repository.StudentRepository;
import com.euniversity.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }


    @CrossOrigin
    @GetMapping("/page/{pageNum}")
    public ResponseEntity<List<Student>> getStudentsByPage(@PathVariable("pageNum") int pageNum){
        Pageable pageable = PageRequest.of(pageNum-1, 50);
        Page<Student> student=studentRepository.findAllByOrderByFacNum(pageable);

        return ResponseEntity.ok(student.stream().toList());
    }

    @CrossOrigin
    @GetMapping("/id/{fac_num}")
    public ResponseEntity<Student> getStudent(@PathVariable("fac_num") long fac_num){
        Student student=studentRepository.findById(fac_num).orElseThrow(()-> new NoSuchElementException("No student with id "+fac_num));
        return ResponseEntity.ok(student);
    }

    @CrossOrigin
    @GetMapping("/pages")
    public ResponseEntity<List<Integer>> getPages(){
        long count=studentRepository.count();
        List<Integer> pages=new ArrayList<>();
        for(int i=1; i<=count/50;i++){
            pages.add(i);
        }
        if(count%50!=0){
            pages.add(pages.size()+1);
        }
        return ResponseEntity.ok(pages);
    }
    @CrossOrigin
    @GetMapping("/{name}/pages")
    public ResponseEntity<List<Integer>> getPagesByName(@PathVariable("name") String name){
        List<Integer> pages=new ArrayList<>();
        long count = studentService.getStudentsByName(name).size();

        if(count==0){
            pages.add(1);
            return ResponseEntity.ok(pages);
        }

        for(int i=1; i<=count/50;i++){
            pages.add(i);
        }
        if(count%50!=0){
            pages.add(pages.size()+1);
        }
        return ResponseEntity.ok(pages);
    }


    @CrossOrigin
    @GetMapping("/{name}/page/{page}")
    public ResponseEntity<List<Student>> getStudentsByNameByPage(@PathVariable("name") String name,@PathVariable("page") Integer page){
        List<Student> studentPage=null;
        List<Student> allStudents=studentService.getStudentsByName(name);

        if(!allStudents.isEmpty()) {
            if(allStudents.size()==1){
                return ResponseEntity.ok(allStudents);
            } else if (page * 50 > allStudents.size()) {
                studentPage = allStudents.subList((page - 1) * 50, allStudents.size() - 1);
            } else {
                studentPage = allStudents.subList((page - 1) * 50, page * 50);
            }
        }

        return ResponseEntity.ok(studentPage);
    }
    /*@CrossOrigin
    @GetMapping("/{firstname}_{lastname}")
    public ResponseEntity<List<Student>> getStudentsByFullName(
            @PathVariable("firstname") String firstname,
            @PathVariable("lastname") String lastname){
        List<Student> students=studentRepository.findStudentsByFirstNameLikeIgnoreCaseAndLastNameLikeIgnoreCase(firstname,lastname);
        return ResponseEntity.ok(students);
    }*/

    @CrossOrigin
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentByOneName(
            @PathVariable("name") String name){
        return ResponseEntity.ok(studentService.getStudentsByName(name));
    }

    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        // TODO: Return save and return response if ok
        studentService.updateStudent(student);
        return ResponseEntity.ok(student);
    }


}
