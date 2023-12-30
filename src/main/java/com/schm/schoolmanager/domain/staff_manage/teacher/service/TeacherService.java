package com.schm.schoolmanager.domain.staff_manage.teacher.service;

import com.schm.schoolmanager.domain.fund_info.qualified.entity.Qualified;
import com.schm.schoolmanager.domain.fund_info.qualified.service.QualifiedService;
import com.schm.schoolmanager.domain.staff_manage.teacher.repository.TeacherRepository;
import com.schm.schoolmanager.domain.subject.dto.SubjectDto;
import com.schm.schoolmanager.domain.staff_manage.teacher.dto.TeacherSaveRequest;
import com.schm.schoolmanager.domain.staff_manage.teacher.dto.TeacherResponseDto;
import com.schm.schoolmanager.domain.subject.entity.Subject;
import com.schm.schoolmanager.domain.staff_manage.teacher.entity.Teacher;
import com.schm.schoolmanager.domain.staff_manage.teacher.mapper.TeacherMapper;
import com.schm.schoolmanager.common.util.ValidateUtil;
import com.schm.schoolmanager.domain.subject.service.SubjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repo;
    private final TeacherMapper mapper;

    private final SubjectService subjectService;
    private final QualifiedService qualifiedService;

    @Transactional
    public List<TeacherResponseDto> getAll() {
        return repo.findAllCustom();
    }

    @Transactional
    public Teacher get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 교사입니다."));
    }

    @Transactional
    public List<Teacher> getMultiple(List<Long> ids) {
        return repo.findAllById(ids);
    }

    @Transactional
    public void save(TeacherSaveRequest dto) {
        Teacher entity = mapper.toEntity(dto);
        Subject subject = subjectService.get(dto.getSubjectId());
        Qualified q = qualifiedService.get(dto.getQualifiedCode());
        entity.setSubject(subject);
        entity.setQualified(q);
        repo.save(entity);
    }

    @Transactional
    public void bulkSave(List<TeacherSaveRequest> dtoList) {
        List<Long> subjectIds = subjectService.getAll()
                .stream()
                .map(SubjectDto::getId)
                .toList();
        List<Long> dtoSubjectIds = dtoList
                .stream()
                .map(TeacherSaveRequest::getSubjectId).toList();
        List<Qualified> qualifieds = qualifiedService.getAllEntity();

        boolean validatedIds = ValidateUtil.isContainId(subjectIds,dtoSubjectIds);

        if(!validatedIds) {
            throw new IllegalArgumentException("유효하지 않은 과목 번호입니다!");
        }

        List<Subject> subjects = subjectService.getMultiple(dtoSubjectIds);
        repo.saveAll(
                dtoList.stream().map(dto -> {
                    Subject subject = subjects.stream().filter(entity -> dto.getSubjectId().equals(entity.getId())).findFirst().get();
                    Qualified q = qualifieds.stream().filter(qf -> dto.getQualifiedCode().equals(qf.getCode())).findFirst().get();
                    Teacher teacher = mapper.toEntity(dto);
                    teacher.setSubject(subject);
                    teacher.setQualified(q);
                    return teacher;
                }).toList()
        );

    }

    @Transactional
    public void remove(Long id) {
        repo.deleteById(id);
    }

}
