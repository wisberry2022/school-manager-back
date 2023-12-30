package com.schm.schoolmanager.domain.fund_info.position.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PositionUpdateRequest {

    private Long id;
    private String name;
    private String code;

}
