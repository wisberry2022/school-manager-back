package com.schm.schoolmanager.domain.staff_manage.teacher.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherResponseDto {

    private Long id;
    private String name;
    private Long classId;
    private String className;
    private String subjectName;

    private String qualified;

}
