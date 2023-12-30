package com.schm.schoolmanager.domain.classes.controller;

import com.schm.schoolmanager.common.constant.GradeType;
import com.schm.schoolmanager.domain.classes.dto.ClassRoomReadResponse;
import com.schm.schoolmanager.domain.classes.dto.ClassRoomSaveRequest;
import com.schm.schoolmanager.domain.classes.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v0/class")
public class ClassController {

    private final ClassService service;

    @GetMapping
    public ResponseEntity<List<ClassRoomReadResponse>> getAll() {
        List<ClassRoomReadResponse> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ClassRoomSaveRequest dto) {
        service.save(dto);
        return ResponseEntity.ok("등록되었습니다.");
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.ok("삭제되었습니다.");
    }

    @PostMapping(value = "/bulk")
    public ResponseEntity<String> bulkSave(@RequestBody List<ClassRoomSaveRequest> requests) {
        service.bulkSave(requests);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<ClassRoomReadResponse>> getAllByGradeType(@PathVariable GradeType grade) {
        List<ClassRoomReadResponse> list = service.getClassesByGrade(grade);
        return ResponseEntity.ok(list);
    }

}
