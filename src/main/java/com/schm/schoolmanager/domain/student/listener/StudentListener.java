package com.schm.schoolmanager.domain.student.listener;

import com.schm.schoolmanager.domain.student.entity.Student;
import jakarta.persistence.PrePersist;

import java.util.List;


public class StudentListener {

    @PrePersist
    public void checkClassPresident(Student student) {
        if(student.getRoom() == null) {
            student.setClassPresident(false);
        }

        if(!(student.getRoom() == null)) {
            List<Student> roomStudents = student.getRoom().getStudents();
            roomStudents.stream()
                    .filter(std -> std.isClassPresident())
                    .findFirst()
                    .ifPresent(std -> {
                        student.setClassPresident(false);
                    });
        }

    }

}
