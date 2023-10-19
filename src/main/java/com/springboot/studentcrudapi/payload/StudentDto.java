package com.springboot.studentcrudapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class StudentDto {

    @NotEmpty(message = "FirstName should not be null or empty")
    private String firstName;
    @NotEmpty(message = "LastName should not be null or empty")
    private String lastName;
    private Integer age;
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
}
