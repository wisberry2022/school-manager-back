package com.schm.schoolmanager.domain.staff_manage.manager_staff.controller;

import com.schm.schoolmanager.domain.staff_manage.manager_staff.dto.ManagerStaffCreateRequest;
import com.schm.schoolmanager.domain.staff_manage.manager_staff.dto.ManagerStaffReadResponse;
import com.schm.schoolmanager.domain.staff_manage.manager_staff.service.ManagerStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v0/manager")
public class ManagerStaffController {

    private final ManagerStaffService service;

    @GetMapping
    public ResponseEntity<List<ManagerStaffReadResponse>> getManagerStaffs() {
        List<ManagerStaffReadResponse> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<String> saveManagerStaff(@RequestBody ManagerStaffCreateRequest request) {
        service.save(request);
        return ResponseEntity.ok("등록되었습니다.");
    }

    @PostMapping(value = "/bulk")
    public ResponseEntity<String> bulkSaveManagerStaff(@RequestBody List<ManagerStaffCreateRequest> requests) {
        service.bulkSave(requests);
        return ResponseEntity.ok("등록되었습니다.");
    }
}
