package com.springboot.studentcrudapi.controller;

import com.springboot.studentcrudapi.payload.StudentDto;
import com.springboot.studentcrudapi.payload.StudentResponseDto;
import com.springboot.studentcrudapi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // create the create rest api
    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    // Get all student rest api
    @GetMapping
    public List<StudentResponseDto> getAllStudents(){

        return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@Valid @RequestBody StudentDto studentDto, @PathVariable(name = "id") Long id) {
        StudentResponseDto updateResponse = studentService.updateStudent(studentDto, id);
        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") Long id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Student Record Deleted Successfully", HttpStatus.OK);
    }
}
