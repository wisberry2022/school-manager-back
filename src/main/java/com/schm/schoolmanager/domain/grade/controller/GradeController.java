package com.schm.schoolmanager.domain.grade.controller;

import com.schm.schoolmanager.common.constant.GradeType;
import com.schm.schoolmanager.domain.grade.service.GradeService;
import com.schm.schoolmanager.domain.grade.dto.GradesDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v0/grade")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService service;

    @GetMapping
    public ResponseEntity<List<GradesDetailResponse>> getAll() {
        List<GradesDetailResponse> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/{grade}")
    public ResponseEntity<String> register(@PathVariable GradeType grade) {
        service.register(grade);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @PostMapping
    public ResponseEntity<String> init() {
        service.init();
        return ResponseEntity.ok("등록되었습니다.");
    }

}
