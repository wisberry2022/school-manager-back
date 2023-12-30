package com.schm.schoolmanager.domain.subject.service;

import com.schm.schoolmanager.domain.subject.entity.Subject;
import com.schm.schoolmanager.domain.subject.repository.SubjectRepository;
import com.schm.schoolmanager.domain.subject.dto.SubjectDto;
import com.schm.schoolmanager.domain.subject.mapper.SubjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository repo;
    private final SubjectMapper mapper;

    @Transactional
    public void save(List<SubjectDto> dtoList) {
        List<Subject> entity = mapper.toEntity(dtoList);
        repo.saveAll(entity);
    }

    @Transactional
    public List<SubjectDto> getAll() {
        List<Subject> list = repo.findAll();
        return mapper.toDto(list);
    }

    @Transactional
    public List<Subject> getMultiple(List<Long> ids) {
        return repo.findAllById(ids);
    }

    @Transactional
    public Subject get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 과목입니다!"));
    }

    @Transactional
    public void remove(Long id) {
        repo.deleteById(id);
    }


}
