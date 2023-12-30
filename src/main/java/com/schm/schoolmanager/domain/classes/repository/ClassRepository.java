package com.schm.schoolmanager.domain.classes.repository;

import com.schm.schoolmanager.domain.classes.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassRepository extends JpaRepository<ClassRoom, Long> {

}
