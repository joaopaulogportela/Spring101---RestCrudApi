package com.luv2tocode.demo.rest;

import com.luv2tocode.demo.entity.Student;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class StudentRestController {
    @GetMapping("/students")
    public List<Student> getStudentList(){
        List<Student> list = new ArrayList<>(List.of(
                new Student("111","Jonh","Paulo"),
                new Student("222","Joao","Paul"),
                new Student("333","Jonh","Paul")));

        return list;
    }
}
