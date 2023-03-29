package com.slashcs.students.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.Reference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Student")
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "student_sequence"
    )
    @Column(
            updatable = false,
            nullable = false
    )
    private long id;

    @Column(
            updatable = false,
            nullable = false
    )
    private String firstname;

    @Column(
            updatable = false,
            nullable = false
    )
    private String lastname;

    @Column(
            updatable = false,
            nullable = false,
            unique = true
    )
    private String username;

    @Column(
            updatable = false,
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            updatable = false,
            nullable = false
    )
    private Date dob;

    @Column(
            updatable = false,
            nullable = false
    )
    private String password;

    @OneToOne(
            mappedBy = "student",
            orphanRemoval = true // delete the student card as well
    )
    private StudentIdCard studentIdCard;


    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.EAGER //
    )
    private List<Book> books = new ArrayList<>();

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "enrollment",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    foreignKey = @ForeignKey(name = "enrollment_student_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    foreignKey = @ForeignKey(name = "enrollment_course_id_fk")
            )
    )
    private List<Course> courses = new ArrayList<>();


    public Student(String fName, String lastName, String username, String email, Date dob, String password) {
        this.firstname = fName;
        this.lastname = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = dob;
    }

    /*
     * This helps ensure that book and student are sync,
     * since this is a bidirectional relationship
     */
    public void addBook(Book book) {
        if (!this.books.contains(book)) {
            this.books.add(book);
            book.setStudent(this);
            ;
        }
    }

    public void removeBook(Book book) {
        if (this.books.contains(book)) {
            book.setStudent(null);
            this.books.remove(book);
        }
    }

    public void enrollCourse(Course course) {
        if (!this.courses.contains(course)) {
            this.courses.add(course);
            course.getStudents().add(this);
        }
    }

    public void unrollCourse(Course course) {
        if (this.courses.contains(course)) {
            this.courses.remove(course);
            course.getStudents().remove(this);
        }
    }

    public List<Book> getBooks() {
        return this.books;
    }

    @Override
    public String toString() {
        return "Student [" + this.firstname + " | " + this.lastname + " ] -> Books: " +
                "" + "]";
    }

}
