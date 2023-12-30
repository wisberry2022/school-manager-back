package com.schm.schoolmanager.domain.staff_manage.manager_staff.dto;

import com.schm.schoolmanager.common.constant.QualifiedType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ManagerStaffReadResponse {

    private Long id;
    private String name;
    private QualifiedType qualified;

}
