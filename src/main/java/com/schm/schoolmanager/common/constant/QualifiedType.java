package com.schm.schoolmanager.common.constant;

public enum QualifiedType {

    PRINCIPLE("PRINCIPLE"),
    VICE_PRINCIPLE("VICE"),
    LICENSED("LICENSED"),
    CONTRACT("CONTRACT"),
    TRAINEE("TRAINEE");

    private final String code;

    QualifiedType(String code) {
        this.code = code;
    }

    public String getValue() {
        return code;
    }

    public static QualifiedType getName(String value) {
        QualifiedType[] list = QualifiedType.values();
        QualifiedType result = null;
        for(QualifiedType qualified:list) {
            boolean isData = qualified.getValue().equals(value);
            if(isData) result = qualified;
        }
        return result;
    }

    public static boolean isSettableQualified(QualifiedType value) {
        if(QualifiedType.LICENSED.equals(value)) {
            return true;
        }
        return false;
    }

}
