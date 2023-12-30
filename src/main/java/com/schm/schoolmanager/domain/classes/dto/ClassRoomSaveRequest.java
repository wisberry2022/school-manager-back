package com.schm.schoolmanager.domain.classes.dto;

import com.schm.schoolmanager.common.constant.GradeType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ClassRoomSaveRequest {

    private String name;
    private Long teacherId;

    private GradeType grade;


}
