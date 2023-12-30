package com.schm.schoolmanager.domain.staff_manage.manager_staff.entity;

import com.schm.schoolmanager.domain.staff_manage.manager_staff.dto.ManagerStaffCreateRequest;
import com.schm.schoolmanager.domain.staff_manage.manager_staff.dto.ManagerStaffReadResponse;
import com.schm.schoolmanager.domain.fund_info.qualified.entity.Qualified;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ManagerStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "qualified_id")
    private Qualified qualified;

    public static ManagerStaff of(ManagerStaffCreateRequest request, Qualified qualified) {
        return ManagerStaff
                .builder()
                .name(request.getName())
                .qualified(qualified)
                .build();
    }

    public ManagerStaffReadResponse toDto() {
        return ManagerStaffReadResponse
                .builder()
                .id(this.id)
                .name(this.name)
                .qualified(this.qualified.getQualifiedType())
                .build();
    }

}
