package com.luv2tocode.demo.rest;

import com.luv2tocode.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> studentlist;
    @PostConstruct
    public void loadData(){
        this.studentlist = new ArrayList<>(List.of(
                new Student(111,"Jonh","Paulo"),
                new Student(222,"Joao","Paul"),
                new Student(333,"Jonh","Paul")));
    }
    @GetMapping("/students")
    public List<Student> getStudentList(){
        return studentlist;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        Student student = studentlist.stream()
                .filter(s -> s.getCode()==studentId)
                .findFirst()
                .orElse(null);

        if(student == null){
            throw new StudentNotFoundException("Stundent with id "+ studentId +" notfound");
        }
        return student;
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> exceptionHandler(StudentNotFoundException exc){
        StudentErrorResponse error = new StudentErrorResponse();

        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(LocalDateTime.now().toString());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> exceptionHandlerGlobal(Exception exc){
        StudentErrorResponse error = new StudentErrorResponse();

        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(LocalDateTime.now().toString());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
