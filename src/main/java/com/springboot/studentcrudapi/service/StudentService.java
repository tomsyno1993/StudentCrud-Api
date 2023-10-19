package com.springboot.studentcrudapi.service;

import com.springboot.studentcrudapi.payload.StudentDto;
import com.springboot.studentcrudapi.payload.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentDto studentDto);

    List<StudentResponseDto> getAllStudent();

    StudentResponseDto getStudentById(Long id);

    StudentResponseDto updateStudent(StudentDto studentDto, Long id);

    void deleteStudentById(Long id);
}
