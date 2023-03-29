package com.slashcs.students.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Book")
public class Book {

    @Id
    @SequenceGenerator(
            sequenceName = "book_sequence",
            name = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            updatable = false,
            nullable = false
    )
    private Long id;

    @Column(
            nullable = false
    )
    private String title;

    @Column(
            nullable = false
    )
    private int pages;

    @ManyToOne
    @JoinColumn (
            name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_book_fk")
    )
    private Student student;

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    @Override
    public String toString () {
        return "Book [title: " + this.title +" | Page: " + this.pages + "]" ;
    }
}
