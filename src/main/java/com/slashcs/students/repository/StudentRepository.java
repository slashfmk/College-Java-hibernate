package com.slashcs.students.repository;

import com.slashcs.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Java Persistence Language
    @Query("select s from Student s where s.email = ?1 or s.username = ?2")
    public Optional<Student> findByEmailOrUsername(String email, String username);

    @Transactional
    @Modifying
    @Query("delete from Student s where s.id = ?1")
    public int deleteStudent(Long id);

    @Transactional
    @Modifying
    @Query("update Student s set s.username = ?1 where s.id = ?1 ")
    public int updateStudent(Student student, Long id);

    public Optional<Student> findByEmail(String email);
    public Optional<Student> findByUsername (String username);

}
