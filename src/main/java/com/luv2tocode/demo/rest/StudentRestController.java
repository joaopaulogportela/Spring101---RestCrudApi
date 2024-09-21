package com.luv2tocode.demo.rest;

import com.luv2tocode.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> studentlist;
    @PostConstruct
    public void loadData(){
        this.studentlist = new ArrayList<>(List.of(
                new Student("111","Jonh","Paulo"),
                new Student("222","Joao","Paul"),
                new Student("333","Jonh","Paul")));
    }
    @GetMapping("/students")
    public List<Student> getStudentList(){
        return studentlist;
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Object> getStudentById(@PathVariable String studentId){
        Student student = studentlist.stream()
                .filter(s -> s.getCode().equals(studentId))
                .findFirst()
                .orElse(null);

        return (student == null)
                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Student with id " + studentId + " not found.")
                : ResponseEntity.ok(student);
    }
}
