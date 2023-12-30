package com.schm.schoolmanager.domain.student.dto;

import com.schm.schoolmanager.common.constant.GradeType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentReadResponse {

    private Long id;
    private String name;
    private boolean classPresident;
    private Long classId;
    private String className;

    private GradeType grade;

}
