package com.schm.schoolmanager.domain.student.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.schm.schoolmanager.common.constant.GradeType;
import com.schm.schoolmanager.domain.classes.entity.QClassRoom;
import com.schm.schoolmanager.domain.grade.entity.QGrade;
import com.schm.schoolmanager.domain.student.dto.StudentReadResponse;
import com.schm.schoolmanager.domain.student.entity.QStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl {

    private final JPAQueryFactory query;

    public List<StudentReadResponse> findAll() {
        QStudent std = QStudent.student;
        QClassRoom room = QClassRoom.classRoom;

        return query
                .select(
                        Projections.constructor(
                                StudentReadResponse.class,
                                std.id,
                                std.name,
                                std.classPresident,
                                room.id,
                                room.name,
                                std.grade.grade
                        )
                )
                .from(std)
                .leftJoin(room).on(std.room.id.eq(room.id))
                .fetch();
    }

    public List<StudentReadResponse> findAll(GradeType grade) {
        QStudent std = QStudent.student;
        QClassRoom room = QClassRoom.classRoom;
        QGrade g = QGrade.grade1;

        return query
                .select(
                        Projections.constructor(
                                StudentReadResponse.class,
                                std.id,
                                std.name,
                                std.classPresident,
                                room.id,
                                room.name,
                                std.grade.grade
                        )
                )
                .from(std)
                .leftJoin(g).on(std.grade.id.eq(g.id))
                .leftJoin(room).on(std.room.id.eq(room.id))
                .where(g.grade.eq(grade))
                .fetch();
    }
}
