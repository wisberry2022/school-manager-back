package com.schm.schoolmanager.domain.classes.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.schm.schoolmanager.common.constant.GradeType;
import com.schm.schoolmanager.domain.classes.dto.ClassRoomReadResponse;
import com.schm.schoolmanager.domain.classes.entity.QClassRoom;
import com.schm.schoolmanager.domain.grade.entity.QGrade;
import com.schm.schoolmanager.domain.staff_manage.teacher.entity.QTeacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassRepositoryImpl {

    private final JPAQueryFactory query;

    public List<ClassRoomReadResponse> findClassRooms(GradeType grade) {
        QClassRoom cr = QClassRoom.classRoom;
        QTeacher t = QTeacher.teacher;

        return query.select(
                Projections.constructor(
                        ClassRoomReadResponse.class,
                        cr.id,
                        cr.name,
                        t.id,
                        t.name)
                )
                .from(cr)
                .leftJoin(t).on(cr.homeroomTeacher.id.eq(t.id))
                .where(gradeEq(cr.grade, grade))
                .fetch();
    }


    private BooleanExpression gradeEq(QGrade entity, GradeType grade) {
        if(grade == null) {
            return null;
        }
        return entity.grade.eq(grade);
    }

}
