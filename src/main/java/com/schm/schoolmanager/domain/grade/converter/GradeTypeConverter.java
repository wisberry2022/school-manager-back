package com.schm.schoolmanager.domain.grade.converter;

import com.schm.schoolmanager.common.constant.GradeType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GradeTypeConverter implements AttributeConverter<GradeType, String> {

    @Override
    public String convertToDatabaseColumn(GradeType attribute) {
        return attribute.getValue();
    }

    @Override
    public GradeType convertToEntityAttribute(String dbData) {
        GradeType result = GradeType.getName(dbData);
        return result == null ? GradeType.DEFAULT_VALUE : result;
    }
}
