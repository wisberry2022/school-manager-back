package com.schm.schoolmanager.domain.student.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentSaveRequest {

    private String name;
    private boolean classPresident;
    private Long classId;


}
