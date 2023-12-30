package com.schm.schoolmanager.domain.staff_manage.teacher.mapper;

import com.schm.schoolmanager.domain.staff_manage.teacher.entity.Teacher;
import com.schm.schoolmanager.domain.staff_manage.teacher.dto.TeacherSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target="id", ignore = true)
    @Mapping(target="name", source="dto.name")
    Teacher toEntity(TeacherSaveRequest dto);

}
