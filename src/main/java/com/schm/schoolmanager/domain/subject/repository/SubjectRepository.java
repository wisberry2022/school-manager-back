package com.schm.schoolmanager.domain.subject.repository;

import com.schm.schoolmanager.domain.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
