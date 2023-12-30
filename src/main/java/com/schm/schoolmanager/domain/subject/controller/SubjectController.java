package com.schm.schoolmanager.domain.subject.controller;

import com.schm.schoolmanager.domain.subject.service.SubjectService;
import com.schm.schoolmanager.domain.subject.dto.SubjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/subject")
public class SubjectController {

    private final SubjectService service;

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAll() {
        List<SubjectDto> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody List<SubjectDto> dtoList) {
        service.save(dtoList);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.ok("삭제되었습니다");
    }
}
