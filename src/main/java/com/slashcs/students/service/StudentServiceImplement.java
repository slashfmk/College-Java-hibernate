package com.slashcs.students.service;

import com.slashcs.students.exceptions.ApiRequestException;
import com.slashcs.students.model.Student;
import com.slashcs.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImplement implements StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImplement(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {

        if (student.getFirstname().isEmpty()
                || student.getLastname().isEmpty()
                || student.getUsername().isEmpty()
                || student.getEmail().isEmpty()
                || student.getPassword().isEmpty()

        ) throw new ApiRequestException("Fields are empty", HttpStatus.PRECONDITION_FAILED);

        Optional<Student> studentExistByEmail = this.studentRepository.findByEmail(student.getEmail());
        Optional<Student> studentExistByUsername = this.studentRepository.findByUsername(student.getUsername());

        if (studentExistByEmail.isPresent())
            throw new ApiRequestException(
                    "User already exists with this email",
                    HttpStatus.BAD_REQUEST);

        if (studentExistByUsername.isPresent()
        )
            throw new ApiRequestException("username: " + student.getUsername() + " has already been taken", HttpStatus.BAD_REQUEST);

        this.studentRepository.save(student);

        return student;
    }

    @Override
    public Student studentDelete(long studentId) {

        Optional<Student> findStudent = this.studentRepository.findById(studentId);

        if (findStudent.isEmpty()) throw new ApiRequestException("There is no such user!", HttpStatus.BAD_REQUEST);

        this.studentRepository.deleteById(studentId);

        return findStudent.get();
    }

    @Override
    public Student studentUpdate(Student student) {

        Optional<Student> findStudent = this.studentRepository.findById(student.getId());

        if (findStudent.isEmpty()) throw new ApiRequestException("Operation failed! No such user!!", HttpStatus.BAD_REQUEST);

        this.studentRepository.save(student);

        return findStudent.get();

    }

    @Override
    public Student getStudent(long id) {

        Optional<Student> foundStudent = this.studentRepository.findById(id);

        if (foundStudent.isEmpty()) throw new ApiRequestException("No such user!!", HttpStatus.BAD_REQUEST);
        return foundStudent.get();
    }

    @Override
    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }
}
