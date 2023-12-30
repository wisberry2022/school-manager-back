package com.schm.schoolmanager.domain.fund_info.task.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TaskCreateRequest {

    private String code;
    private String name;

}
