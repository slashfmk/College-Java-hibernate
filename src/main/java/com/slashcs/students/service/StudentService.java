package com.slashcs.students.service;

import com.slashcs.students.model.Student;

import java.util.List;

public interface StudentService {

    public Student addStudent(Student student);

    public Student studentDelete(long studentId);

    public Student studentUpdate(Student student);

    public Student getStudent(long id);

    public List<Student> getAllStudents();

}
