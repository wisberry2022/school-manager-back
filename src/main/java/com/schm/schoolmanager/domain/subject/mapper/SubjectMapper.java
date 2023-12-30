package com.schm.schoolmanager.domain.subject.mapper;

import com.schm.schoolmanager.domain.subject.dto.SubjectDto;
import com.schm.schoolmanager.domain.subject.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "id", ignore = true)
    List<Subject> toEntity(List<SubjectDto> dto);

    List<SubjectDto> toDto(List<Subject> entity);

}
