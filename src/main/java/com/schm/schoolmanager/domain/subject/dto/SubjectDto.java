package com.schm.schoolmanager.domain.subject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectDto {

    private Long id;
    private String name;
    private String code;

}
