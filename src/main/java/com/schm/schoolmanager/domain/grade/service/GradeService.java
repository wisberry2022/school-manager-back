package com.schm.schoolmanager.domain.grade.service;

import com.schm.schoolmanager.common.constant.GradeType;
import com.schm.schoolmanager.domain.grade.entity.Grade;
import com.schm.schoolmanager.domain.grade.repository.GradeRepository;
import com.schm.schoolmanager.domain.grade.dto.GradesDetailResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository repo;

    @Transactional
    public Grade get(GradeType grade) {
        return repo.findByGrade(grade).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 학년 정보입니다!"));
    }

    @Transactional
    public List<Grade> getAllEntity() {
        return repo.findAll();
    }


    @Transactional
    public List<GradesDetailResponse> getAll() {
        List<Grade> grades = repo.findAll();
        return grades.stream()
                .map(grade -> Grade.toDto(grade))
                .toList();
    }

    @Transactional
    public void register(GradeType grade) {
        Grade entity = Grade.of(grade);
        repo.save(entity);
    }

    @Transactional
    public void init() {
        List<GradeType> list = Arrays.stream(GradeType.values()).toList();
        list.stream().forEach(
            type -> {
                if (!type.getValue().equals("0")) {
                    register(type);
                }
            }
        );
    }


}
