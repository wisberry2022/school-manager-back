package com.schm.schoolmanager.common.util;

import com.schm.schoolmanager.common.constant.GradeType;

import java.util.Arrays;
import java.util.List;

public class ValidateUtil {

    public static boolean isContainId(List<Long> origin, List<Long> target) {
        List<Long> data = target.stream().filter(id -> !origin.contains(id)).toList();
        return data.size() == 0;
    }

    public static boolean isContainGrade(List<GradeType> grades) {
        List<GradeType> types = Arrays.stream(GradeType.values()).toList();
        List<GradeType> data = grades.stream().filter(grade -> !types.contains(grade)).toList();
        return data.size() == 0;
    }

}
