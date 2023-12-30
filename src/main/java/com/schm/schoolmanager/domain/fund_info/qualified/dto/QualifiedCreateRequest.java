package com.schm.schoolmanager.domain.fund_info.qualified.dto;

import com.schm.schoolmanager.common.constant.QualifiedType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QualifiedCreateRequest {

    private String code;
    private String name;
    private QualifiedType type;

}
