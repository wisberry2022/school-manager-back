package com.schm.schoolmanager.domain.grade.repository;

import com.schm.schoolmanager.common.constant.GradeType;
import com.schm.schoolmanager.domain.grade.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    Optional<Grade> findByGrade(GradeType grade);

}
