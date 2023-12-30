package com.schm.schoolmanager.domain.grade.entity;

import com.schm.schoolmanager.common.constant.GradeType;
import com.schm.schoolmanager.domain.grade.dto.GradesDetailResponse;
import com.schm.schoolmanager.domain.grade.converter.GradeTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Convert(converter = GradeTypeConverter.class)
    private GradeType grade;

    private String memo;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grade")
//    private List<ClassRoom> classes;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grade")
//    private List<Student> students;

    public static Grade of(GradeType grade) {
        return Grade
                .builder()
                .grade(grade)
                .memo(grade.getValue().concat("학년"))
                .build();
    }

    public static GradesDetailResponse toDto(Grade grade) {
        return GradesDetailResponse
                .builder()
                .grade(grade.getGrade())
                .memo(grade.getMemo())
                .build();
    }

}
