package com.schm.schoolmanager.domain.student.entity;

import com.schm.schoolmanager.domain.classes.entity.ClassRoom;
import com.schm.schoolmanager.domain.student.listener.AutoGradeListener;
import com.schm.schoolmanager.domain.student.listener.StudentListener;
import com.schm.schoolmanager.domain.grade.entity.Grade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners({StudentListener.class, AutoGradeListener.class})
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder.Default
    @Column(columnDefinition = "boolean default false")
    private boolean classPresident = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassRoom room;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    public void setClass(ClassRoom room) {
        this.room = room;
    }

    public void setClassPresident(boolean value) {
        this.classPresident = value;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

}
