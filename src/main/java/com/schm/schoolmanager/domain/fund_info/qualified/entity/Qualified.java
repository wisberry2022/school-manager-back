package com.schm.schoolmanager.domain.fund_info.qualified.entity;

import com.schm.schoolmanager.common.constant.QualifiedType;
import com.schm.schoolmanager.domain.fund_info.qualified.dto.QualifiedCreateRequest;
import com.schm.schoolmanager.domain.fund_info.qualified.converter.QualifiedTypeConverter;
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
public class Qualified {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    @Convert(converter = QualifiedTypeConverter.class)
    private QualifiedType qualifiedType;

    private String name;

    public static Qualified of(QualifiedCreateRequest request) {
        return Qualified
                .builder()
                .code(request.getCode())
                .name(request.getName())
                .qualifiedType(request.getType())
                .build();
    }

}
