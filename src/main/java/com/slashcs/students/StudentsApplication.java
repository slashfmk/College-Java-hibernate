package com.slashcs.students;

import com.github.javafaker.Faker;
import com.slashcs.students.model.*;
import com.slashcs.students.repository.BookRepository;
import com.slashcs.students.repository.StudentIdCardRepository;
import com.slashcs.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class StudentsApplication {


    public static void main(String[] args) {
        SpringApplication.run(StudentsApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository,
            BookRepository bookRepository) {

        return args -> {

            var faker = new Faker();

            var student = new Student(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.name().username(),
                    "xxx@gmail.com",
                    faker.date().birthday(),
                    faker.name().fullName());

            var student2 = new Student(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.name().username(),
                    "jaja@gmail.com",
                    faker.date().birthday(),
                    faker.name().fullName());

            var cardId1 = new StudentIdCard(1123465490, student);
            var cardId2 = new StudentIdCard(3475674, student2);

            var book1 = new Book("Harry potter", 458);
            var book2 = new Book("Super human", 100);
            var book3 = new Book("Data structure and Algorythm", 898);

            var course1 = new Course("Math 102", "Mathematics");
            var course2 = new Course("Coms 327", "Computer Science");

            student.addBook(book1);
            student.addBook(book2);
            student.addBook(book3);


            // student.addEnrollments(new Enrollment(student, course1));
//            student2.enrollCourse(course2);
//            student2.enrollCourse(course1);

            studentIdCardRepository.save(cardId1);
           // studentIdCardRepository.save(cardId2);

            studentRepository.findById(1L)
                    .ifPresent(System.out::println);
            // System.out.println(student);
            //  studentRepository.deleteById(1L);

        };
    }


    private void generateRandomStudent(StudentRepository studentRepository) {

        var faker = new Faker();

        for (int i = 0; i < 5; i++) {

            Student st = new Student(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.name().username(),
                    faker.name().firstName() + i + "@gmail.com",
                    faker.date().birthday(),
                    faker.name().fullName());

            studentRepository.save(st);
        }
    }

}
