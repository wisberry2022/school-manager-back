package com.schm.schoolmanager.domain.classes.mapper;

import com.schm.schoolmanager.domain.classes.dto.ClassRoomSaveRequest;
import com.schm.schoolmanager.domain.classes.entity.ClassRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ClassesMapper {

    @Mapping(target="id", ignore = true)
    ClassRoom toEntity(ClassRoomSaveRequest dto);

}
