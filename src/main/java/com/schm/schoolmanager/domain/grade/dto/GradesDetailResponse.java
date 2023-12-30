package com.schm.schoolmanager.domain.grade.dto;

import com.schm.schoolmanager.common.constant.GradeType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GradesDetailResponse {

    private GradeType grade;
    private String memo;

}
