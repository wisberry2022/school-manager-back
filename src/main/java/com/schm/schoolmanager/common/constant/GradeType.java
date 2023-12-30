package com.schm.schoolmanager.common.constant;

public enum GradeType {

    FIRST_YEAR("1"), SECOND_YEAR("2"), THIRD_YEAR("3"), DEFAULT_VALUE("0");

    private final String value;

    GradeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GradeType getName(String value) {
        GradeType[] list = GradeType.values();
        GradeType result = null;
        for(GradeType grade:list) {
            boolean isData = grade.getValue().equals(value);
            if(isData) result = grade;
        }
        return result;
    }

}
