package com.schm.schoolmanager.domain.fund_info.qualified.converter;

import com.schm.schoolmanager.common.constant.QualifiedType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class QualifiedTypeConverter implements AttributeConverter<QualifiedType, String> {


    @Override
    public String convertToDatabaseColumn(QualifiedType attribute) {
        return attribute.getValue();
    }

    @Override
    public QualifiedType convertToEntityAttribute(String dbData) {
        QualifiedType type = QualifiedType.getName(dbData);
        return type;
    }
}
