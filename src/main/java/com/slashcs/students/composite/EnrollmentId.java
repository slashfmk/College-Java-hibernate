package com.slashcs.students.composite;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class EnrollmentId implements Serializable {

    @Column(
            name = "student_id"
    )
    private Long studentId;

    @Column(
            name = "course_id"
    )
    private Long courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
