package com.schm.schoolmanager.domain.classes.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ClassRoomReadResponse {

    private Long id;
    private String name;
    private Long teacherId;
    private String teacher;

}
