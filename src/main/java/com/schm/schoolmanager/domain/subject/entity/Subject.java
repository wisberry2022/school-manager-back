package com.schm.schoolmanager.domain.subject.entity;

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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @OneToMany(mappedBy = "subject")
    private List<Teacher> teachers;

}
