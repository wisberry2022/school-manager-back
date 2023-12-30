package com.schm.schoolmanager.domain.staff_manage.teacher.controller;

import com.schm.schoolmanager.domain.fund_info.position.dto.PositionMapSaveRequest;
import com.schm.schoolmanager.domain.fund_info.position.service.PositionService;
import com.schm.schoolmanager.domain.staff_manage.teacher.service.TeacherService;
import com.schm.schoolmanager.domain.staff_manage.teacher.dto.TeacherSaveRequest;
import com.schm.schoolmanager.domain.staff_manage.teacher.dto.TeacherResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v0/teacher")
public class TeacherController {

    private final TeacherService service;
    private final PositionService positionService;

    @GetMapping
    public ResponseEntity<List<TeacherResponseDto>> getAll() {
        List<TeacherResponseDto> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody TeacherSaveRequest request) {
        service.save(request);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @PostMapping("/bulk")
    public ResponseEntity<String> bulkSave(@RequestBody List<TeacherSaveRequest> requests) {
        service.bulkSave(requests);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.ok("삭제되었습니다.");
    }

    @DeleteMapping(value = "/{id}/position/{positionId}")
    public ResponseEntity<String> clearPosition(@PathVariable Long id, @PathVariable Long positionId) {
        positionService.clearPosition(id, positionId);
        return ResponseEntity.ok("직위가 해제되었습니다.");
    }

    @PostMapping(value = "/position")
    public ResponseEntity<String> setPosition(@RequestBody PositionMapSaveRequest request) {
        positionService.setPosition(request);
        return ResponseEntity.ok("등록되었습니다.");
    }

}
