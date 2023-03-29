package com.slashcs.students.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Course")
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "course_sequence"
    )
    @Column(
            nullable = false,
            updatable = false
    )
    private Long id;

    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            nullable = false
    )
    private String department;

    @ManyToMany(
            mappedBy = "courses"
    )
    private List<Student> students = new ArrayList<>();


    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }


    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", students=" + students +
                '}';
    }
}
