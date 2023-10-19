package com.springboot.studentcrudapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "student",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
)

public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name = "First_Name", nullable = false)
    private String firstName;
    @Column(name = "Last_Name", nullable = false)
    private String lastName;
    @Column(name = "Age", nullable = false)
    private Integer age;
    @Column(name = "Email", nullable = false)
    private String email;
}
