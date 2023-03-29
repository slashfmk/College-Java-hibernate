package com.slashcs.students.controller;

import com.slashcs.students.model.Student;
import com.slashcs.students.service.StudentService;
import com.slashcs.students.service.StudentServiceImplement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentServiceImplement studentService;

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {

        this.studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("New student successfully created");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {

        var allStudents = this.studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(allStudents);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {

        var found = this.studentService.studentDelete(id);
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student updateStudent, @PathVariable long id) {

        updateStudent.setId(id);
        var updatedStudent = this.studentService.studentUpdate(updateStudent);

        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
    }


}
