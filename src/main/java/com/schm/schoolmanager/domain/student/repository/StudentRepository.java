package com.schm.schoolmanager.domain.student.repository;

import com.schm.schoolmanager.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {

}
