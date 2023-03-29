package com.slashcs.students.repository;

import com.slashcs.students.model.StudentIdCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentIdCardRepository extends JpaRepository <StudentIdCard, Long> {


}
