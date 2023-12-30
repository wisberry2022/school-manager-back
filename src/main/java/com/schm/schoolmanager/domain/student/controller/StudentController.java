package com.schm.schoolmanager.domain.student.controller;

import com.schm.schoolmanager.common.constant.GradeType;
import com.schm.schoolmanager.domain.student.dto.StudentReadResponse;
import com.schm.schoolmanager.domain.student.dto.StudentSaveRequest;
import com.schm.schoolmanager.domain.student.service.StudentService;
import com.schm.schoolmanager.common.exception.StudentMigrateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/student")
public class StudentController {

    private final StudentService service;

    @GetMapping
    public ResponseEntity<List<StudentReadResponse>> getAll() {
        List<StudentReadResponse> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody StudentSaveRequest request) {
        service.save(request);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @PatchMapping(value="/migrate/{studentId}/{classId}")
    public ResponseEntity<String> migrateClass(@PathVariable Long studentId, @PathVariable Long classId) throws StudentMigrateException {
        service.migrateClass(studentId, classId);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @PostMapping(value = "/bulk")
    public ResponseEntity<String> bulkSave(@RequestBody List<StudentSaveRequest> requests) {
        service.bulkSave(requests);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @GetMapping(value="/class/{id}")
    public ResponseEntity<List<StudentReadResponse>> get(@PathVariable Long id) {
        List<StudentReadResponse> list = service.getStudentsByClass(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/grade/{year}")
    public ResponseEntity<List<StudentReadResponse>> getAll(@PathVariable GradeType year) {
        List<StudentReadResponse> list = service.getAll(year);
        return ResponseEntity.ok(list);
    }
}
