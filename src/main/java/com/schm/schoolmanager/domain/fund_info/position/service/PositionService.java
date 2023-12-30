package com.schm.schoolmanager.domain.fund_info.position.service;

import com.schm.schoolmanager.common.util.RepositoryUtil;
import com.schm.schoolmanager.domain.fund_info.position.dto.PositionMapSaveRequest;
import com.schm.schoolmanager.domain.fund_info.position.dto.PositionReadResponse;
import com.schm.schoolmanager.domain.fund_info.position.dto.PositionSaveRequest;
import com.schm.schoolmanager.domain.fund_info.position.dto.PositionUpdateRequest;
import com.schm.schoolmanager.domain.fund_info.position.entity.Position;
import com.schm.schoolmanager.domain.fund_info.position.entity.PositionMap;
import com.schm.schoolmanager.domain.fund_info.position.repository.PositionRepository;
import com.schm.schoolmanager.domain.staff_manage.teacher.entity.Teacher;
import com.schm.schoolmanager.domain.staff_manage.teacher.service.TeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository repo;

    @Transactional
    public Position get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 직위입니다!"));
    }

    @Transactional
    public Position get(String code) {
        return repo.findByCode(code).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 직위입니다!"));
    }

    @Transactional
    public List<PositionReadResponse> getAll() {
        return RepositoryUtil.getAll(repo, position -> position.toDto());
    }

    @Transactional
    public void register(PositionSaveRequest request) {
        Position position = Position.of(request);
        repo.save(position);
    }

    @Transactional
    public void bulkRegister(List<PositionSaveRequest> requests) {
        RepositoryUtil.saveAll(requests, repo, request -> Position.of(request));
    }

    @Transactional
    public void update(PositionUpdateRequest request) {
        Position p = get(request.getId());
        p.update(request);
        repo.save(p);
    }

    @Transactional
    public void setPosition(PositionMapSaveRequest request) {
        Position p = get(request.getCode());
        p.addItem(p, request.getStaffId());
        repo.save(p);
    }

    @Transactional
    public void clearPosition(Long staffId, Long pId) {
        Position p = get(pId);
        p.removeItem(staffId, pId);
        repo.save(p);
    }

}
