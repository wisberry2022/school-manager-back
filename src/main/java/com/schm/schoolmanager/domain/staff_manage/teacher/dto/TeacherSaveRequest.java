package com.schm.schoolmanager.domain.staff_manage.teacher.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TeacherSaveRequest {

    private Long id;
    private String name;
    private Long subjectId;

    private String qualifiedCode;

}
