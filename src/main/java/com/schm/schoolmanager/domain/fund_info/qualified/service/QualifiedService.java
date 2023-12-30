package com.schm.schoolmanager.domain.fund_info.qualified.service;

import com.schm.schoolmanager.common.constant.QualifiedType;
import com.schm.schoolmanager.domain.fund_info.qualified.dto.QualifiedReadResponse;
import com.schm.schoolmanager.domain.fund_info.qualified.mapper.QualifiedMapper;
import com.schm.schoolmanager.domain.fund_info.qualified.dto.QualifiedCreateRequest;
import com.schm.schoolmanager.domain.fund_info.qualified.entity.Qualified;
import com.schm.schoolmanager.domain.fund_info.qualified.repository.QualifiedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QualifiedService {

    private final QualifiedRepository repo;
    private final QualifiedMapper mapper;

    @Transactional
    public Qualified get(String code) {
        return repo.findByCode(code).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 자격입니다!"));
    }

    @Transactional
    public Qualified get(QualifiedType type) {
        return repo.findByQualifiedType(type).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 계약 형태입니다."));
    }

    @Transactional
    public List<Qualified> getAllEntity() {
        return repo.findAll();
    }

    @Transactional
    public List<QualifiedReadResponse> getAll() {
        return mapper.toDto(repo.findAll());
    }

    @Transactional
    public void create(QualifiedCreateRequest request) {
        Qualified sp = Qualified.of(request);
        repo.save(sp);
    }

    @Transactional
    public void bulkCreate(List<QualifiedCreateRequest> requests) {
        repo.saveAll(
            requests.stream()
                .map(r -> Qualified.of(r))
                .toList()
        );
    }

}
