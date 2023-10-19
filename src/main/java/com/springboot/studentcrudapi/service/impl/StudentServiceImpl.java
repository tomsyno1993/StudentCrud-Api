package com.springboot.studentcrudapi.service.impl;

import com.springboot.studentcrudapi.entity.Student;
import com.springboot.studentcrudapi.exception.DuplicateStudentException;
import com.springboot.studentcrudapi.exception.ResourceNotFoundException;
import com.springboot.studentcrudapi.payload.StudentDto;
import com.springboot.studentcrudapi.payload.StudentResponseDto;
import com.springboot.studentcrudapi.repository.StudentRepository;
import com.springboot.studentcrudapi.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private ModelMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto createStudent(StudentDto studentDto) {
        // Check if a student with the same email already exists
        String email = studentDto.getEmail();
        Student existingStudent = studentRepository.findByEmail(email);

        if (existingStudent != null) {
            // A student with the same email already exists, if not throw a custom exception
            throw new DuplicateStudentException("A student with the same email already exists");
        }
        // convert dto to entity
        Student student = mapToEntity(studentDto);
        Student newStudent = studentRepository.save(student);

        // convert entity to dto
        StudentResponseDto studentResponse = mapToDto(newStudent);

        return studentResponse;
    }

    @Override
    public List<StudentResponseDto> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        return mapToDto(student);
    }

    @Override
    public StudentResponseDto updateStudent(StudentDto studentDto, Long id) {
        // get student by id from the database
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());

        Student updateStudent = studentRepository.save(student);
        return mapToDto(updateStudent);

    }

    @Override
    public void deleteStudentById(Long id) {
        // get student by id from the database
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        studentRepository.delete(student);
    }

    // converting entity to dto
    private StudentResponseDto mapToDto(Student student) {
        StudentResponseDto studentResponseDto = mapper.map(student, StudentResponseDto.class);

//        StudentResponseDto studentResponseDto = new StudentResponseDto();
//        studentResponseDto.setId(student.getId());
//        studentResponseDto.setFirstName(student.getFirstName());
//        studentResponseDto.setLastName(student.getLastName());
//        studentResponseDto.setAge(student.getAge());
//        studentResponseDto.setEmail(student.getEmail());

        return studentResponseDto;
    }

    // converting dto to entity
    private Student mapToEntity(StudentDto studentDto) {
        Student student = mapper.map(studentDto, Student.class);

//        Student student = new Student();
//        student.setId(studentDto.getId());
//        student.setFirstName(studentDto.getFirstName());
//        student.setLastName(studentDto.getLastName());
//        student.setAge(studentDto.getAge());
//        student.setEmail(studentDto.getEmail());

        return student;
    }
}
