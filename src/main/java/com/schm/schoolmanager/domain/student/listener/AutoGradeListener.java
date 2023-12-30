package com.schm.schoolmanager.domain.student.listener;


import com.schm.schoolmanager.domain.grade.entity.Grade;
import com.schm.schoolmanager.domain.student.entity.Student;
import jakarta.persistence.PrePersist;

public class AutoGradeListener {

    @PrePersist
    public void autoMappingGrade(Student student) {
        if(student.getRoom() != null) {
            Grade grade = student.getRoom().getGrade();
            student.setGrade(grade);
        }
    }

}
