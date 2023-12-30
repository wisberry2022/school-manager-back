package com.schm.schoolmanager.domain.classes.service;

import com.schm.schoolmanager.common.constant.GradeType;
import com.schm.schoolmanager.common.constant.QualifiedType;
import com.schm.schoolmanager.common.util.ValidateUtil;
import com.schm.schoolmanager.domain.classes.dto.ClassRoomReadResponse;
import com.schm.schoolmanager.domain.classes.dto.ClassRoomSaveRequest;
import com.schm.schoolmanager.domain.classes.mapper.ClassesMapper;
import com.schm.schoolmanager.domain.classes.repository.ClassRepository;
import com.schm.schoolmanager.domain.classes.repository.ClassRepositoryImpl;
import com.schm.schoolmanager.domain.grade.entity.Grade;
import com.schm.schoolmanager.domain.staff_manage.teacher.dto.TeacherResponseDto;
import com.schm.schoolmanager.domain.classes.entity.ClassRoom;
import com.schm.schoolmanager.domain.staff_manage.teacher.entity.Teacher;
import com.schm.schoolmanager.domain.grade.service.GradeService;
import com.schm.schoolmanager.domain.staff_manage.teacher.service.TeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final TeacherService teacherService;

    private final GradeService gradeService;

    private final ClassesMapper mapper;

    private final ClassRepository repo;
    private final ClassRepositoryImpl QRepo;


    @Transactional
    public List<ClassRoomReadResponse> getAll() {
        return QRepo.findClassRooms(null);
    }

    @Transactional
    public ClassRoom get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 교실입니다!"));
    }

    @Transactional
    public List<ClassRoom> getAllByIds(List<Long> ids) {
        return repo.findAllById(ids);
    }

    @Transactional
    public List<ClassRoomReadResponse> getClassesByGrade(GradeType grade) {
        return QRepo.findClassRooms(grade);
    }

    private Teacher getQualifiedTeacher(ClassRoomSaveRequest request) {
        if(request.getTeacherId() == null) throw new IllegalArgumentException("교실 등록 시 담임교사 지정은 필수입니다.");
        if(request.getGrade() == null) throw new IllegalArgumentException("교실 등록 시 학년 지정은 필수입니다.");
        Teacher teacher = teacherService.get(request.getTeacherId());
        if(!QualifiedType.isSettableQualified(teacher.getQualified().getQualifiedType())) {
            throw new IllegalArgumentException("학급의 담임 지정은 정교사만 가능합니다!");
        };
        return teacher;
    }


    @Transactional
    public void save(ClassRoomSaveRequest request) {
        Teacher teacher = this.getQualifiedTeacher(request);
        Grade grade = gradeService.get(request.getGrade());
        ClassRoom entity = mapper.toEntity(request);
        entity.setTeacher(teacher);
        entity.setGrade(grade);
        repo.save(entity);
    }

    @Transactional
    public void bulkSave(List<ClassRoomSaveRequest> requests) {
        List<Long> requestIds = requests.stream().map(ClassRoomSaveRequest::getTeacherId).toList();
        List<Long> teacherIds = teacherService.getAll().stream().map(TeacherResponseDto::getId).toList();
        List<GradeType> gradeTypes = requests.stream().map(ClassRoomSaveRequest::getGrade).toList();

        boolean isValidIds = ValidateUtil.isContainId(teacherIds, requestIds);
        boolean isValidGrades = ValidateUtil.isContainGrade(gradeTypes);

        if(!isValidIds) {
            throw new IllegalArgumentException("교실 등록 시 담임교사 지정은 필수입니다.");
        }

        if(!isValidGrades) {
            throw new IllegalArgumentException("교실 등록 시 학년 지정은 필수입니다.");
        }

        List<Teacher> teachers = teacherService.getMultiple(requestIds);
        List<Grade> grades = gradeService.getAllEntity();

        repo.saveAll(requests.stream().map(
            request -> {
                Long teacherId = request.getTeacherId();
                Teacher teacher = teachers.stream().filter(t -> t.getId().equals(teacherId)).findFirst().get();
                Grade grade = grades.stream().filter(g -> request.getGrade().equals(g.getGrade())).findFirst().get();
                ClassRoom room = mapper.toEntity(request);
                if(!QualifiedType.isSettableQualified(teacher.getQualified().getQualifiedType())) {
                    throw new IllegalArgumentException("학급의 담임 지정은 정교사만 가능합니다!");
                }
                room.setTeacher(teacher);
                room.setGrade(grade);
                return room;
            }
        ).toList());
    }

    @Transactional
    public void remove(Long id) {
        repo.deleteById(id);
    }


}
