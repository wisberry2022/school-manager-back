package com.schm.schoolmanager.domain.student.mapper;

import com.schm.schoolmanager.domain.student.dto.StudentReadResponse;
import com.schm.schoolmanager.domain.student.dto.StudentSaveRequest;
import com.schm.schoolmanager.domain.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target="id", ignore=true)
    @Mapping(target="grade", ignore = true)
    Student toEntity(StudentSaveRequest request);

    @Mapping(target="classId", source="entity.room.id")
    @Mapping(target="className", source="entity.room.name")
    @Mapping(target="grade", source="entity.grade.grade")
    StudentReadResponse toDto(Student entity);

    default List<StudentReadResponse> toDtoList(List<Student> stdList) {
        if(stdList == null) {
            return null;
        }
        List<StudentReadResponse> result = new ArrayList<>();

        for(Student std:stdList) {
            result.add(
               toDto(std)
            );
        }

        return result;
    }

}
