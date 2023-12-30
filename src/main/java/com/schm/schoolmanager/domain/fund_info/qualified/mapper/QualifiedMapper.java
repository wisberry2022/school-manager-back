package com.schm.schoolmanager.domain.fund_info.qualified.mapper;

import com.schm.schoolmanager.domain.fund_info.qualified.dto.QualifiedReadResponse;
import com.schm.schoolmanager.domain.fund_info.qualified.entity.Qualified;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QualifiedMapper {

    @Mapping(target = "qualified", source="entity.qualifiedType")
    QualifiedReadResponse toDto(Qualified entity);

//    List<StaffPositionResponse> toDto(List<Qualified> entity);

    default List<QualifiedReadResponse> toDto(List<Qualified> entity) {
        if(entity == null) {
            return null;
        }

        List<QualifiedReadResponse> list = new ArrayList<>();

        for(Qualified q:entity) {
            list.add(toDto(q));
        }

        return list;
    }

}
