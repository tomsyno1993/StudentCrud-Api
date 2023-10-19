package com.springboot.studentcrudapi.payload;

import lombok.Data;

@Data
public class StudentResponseDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
}
