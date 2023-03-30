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
@Table(name = "course")
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

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "course"
    )
    private List<Enrollment> enrollments = new ArrayList<>();


    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollments(Enrollment enrollment) {

        if (!this.enrollments.contains(enrollment)) {
            this.enrollments.add(enrollment);
            enrollment.setCourse(this);
        }
    }

    public void removeEnrollments(Enrollment enrollment) {
        this.enrollments.remove(enrollment);
    }


    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", enrollments=" + enrollments +
                '}';
    }
}
