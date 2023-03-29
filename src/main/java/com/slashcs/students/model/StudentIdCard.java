package com.slashcs.students.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "StudentIdCard")
public class StudentIdCard {

    @Id
    @SequenceGenerator(
            name = "student_id_card_sequence",
            sequenceName = "student_id_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_card_sequence"
    )
    @Column(
            updatable = false,
            nullable = false
    )
    private long id;

    @Column(
            unique = true,
            length = 15
    )
    private long cardNumber;


    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_student_id_card_student_fk")
    )
    private Student student;

    public StudentIdCard(long cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }


}
