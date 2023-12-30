package com.schm.schoolmanager.domain.fund_info;

import com.schm.schoolmanager.domain.fund_info.position.dto.PositionMapSaveRequest;
import com.schm.schoolmanager.domain.fund_info.position.dto.PositionReadResponse;
import com.schm.schoolmanager.domain.fund_info.position.dto.PositionSaveRequest;
import com.schm.schoolmanager.domain.fund_info.position.dto.PositionUpdateRequest;
import com.schm.schoolmanager.domain.fund_info.position.service.PositionService;
import com.schm.schoolmanager.domain.fund_info.task.dto.TaskCreateRequest;
import com.schm.schoolmanager.domain.fund_info.task.dto.TaskReadResponse;
import com.schm.schoolmanager.domain.fund_info.task.dto.TaskUpdateRequest;
import com.schm.schoolmanager.domain.fund_info.task.service.TaskService;
import com.schm.schoolmanager.domain.fund_info.qualified.service.QualifiedService;
import com.schm.schoolmanager.domain.fund_info.qualified.dto.QualifiedCreateRequest;
import com.schm.schoolmanager.domain.fund_info.qualified.dto.QualifiedReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v0/fundamental")
public class Controller {

    private final QualifiedService qualifiedService;
    private final TaskService taskService;

    private final PositionService positionService;

    @GetMapping("/qualified")
    public ResponseEntity<List<QualifiedReadResponse>> getQualifies() {
        List<QualifiedReadResponse> list = qualifiedService.getAll();
        return ResponseEntity.of(Optional.ofNullable(list));
    }

    @PostMapping("/qualified")
    public ResponseEntity<String> createQualified(@RequestBody QualifiedCreateRequest request) {
        qualifiedService.create(request);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @PostMapping(value = "/qualified/bulk")
    public ResponseEntity<String> bulkSaveQualified(@RequestBody List<QualifiedCreateRequest> requests) {
        qualifiedService.bulkCreate(requests);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @GetMapping(value = "/task")
    public ResponseEntity<List<TaskReadResponse>> getTasks() {
        List<TaskReadResponse> list = taskService.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/task")
    public ResponseEntity<String> register(@RequestBody TaskCreateRequest request) {
        taskService.register(request);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @PutMapping(value = "/task")
    public ResponseEntity<String> update(@RequestBody TaskUpdateRequest request) {
        taskService.update(request);
        return ResponseEntity.ok("수정되었습니다.");
    }

    @PostMapping(value = "/task/bulk")
    public ResponseEntity<String> bulkRegisterTasks(@RequestBody List<TaskCreateRequest> requests) {
        taskService.bulkRegister(requests);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @GetMapping(value = "/position")
    public ResponseEntity<List<PositionReadResponse>> getPositions() {
        List<PositionReadResponse> list = positionService.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/position")
    public ResponseEntity<String> register(@RequestBody PositionSaveRequest request) {
        positionService.register(request);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @PutMapping(value = "/position")
    public ResponseEntity<String> update(@RequestBody PositionUpdateRequest request) {
        positionService.update(request);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @PostMapping(value = "/position/bulk")
    public ResponseEntity<String> bulkRegisterPositions(@RequestBody List<PositionSaveRequest> requests) {
        positionService.bulkRegister(requests);
        return ResponseEntity.ok("등록되었습니다.");
    }
}
