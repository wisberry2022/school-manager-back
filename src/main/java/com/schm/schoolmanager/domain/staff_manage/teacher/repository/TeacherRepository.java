package com.schm.schoolmanager.domain.staff_manage.teacher.repository;

import com.schm.schoolmanager.domain.staff_manage.teacher.dto.TeacherResponseDto;
import com.schm.schoolmanager.domain.staff_manage.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT new com.schm.schoolmanager.domain.staff_manage.teacher.dto.TeacherResponseDto(" +
            "t.id, " +
            "t.name, " +
            "coalesce(cr.id, 0) ," +
            "coalesce(cr.name, '담임 미지정')," +
            "ts.name, " +
            "t.qualified.name " +
            ") " +
            "FROM Teacher t " +
            "LEFT JOIN ClassRoom cr on t.id = cr.homeroomTeacher.id " +
            "LEFT JOIN t.subject ts ")
    List<TeacherResponseDto> findAllCustom();

    List<Teacher> findAll();

}
