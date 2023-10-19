package com.springboot.studentcrudapi.repository;

import com.springboot.studentcrudapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

}
