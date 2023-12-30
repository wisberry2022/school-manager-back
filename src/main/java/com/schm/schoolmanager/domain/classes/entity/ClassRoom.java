package com.schm.schoolmanager.domain.classes.entity;

import com.schm.schoolmanager.domain.grade.entity.Grade;
import com.schm.schoolmanager.domain.student.entity.Student;
import com.schm.schoolmanager.domain.staff_manage.teacher.entity.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Student> students;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher homeroomTeacher;

    @ManyToOne
    private Grade grade;

    public void setTeacher(Teacher teacher) {
        this.homeroomTeacher = teacher;
    };

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

}
