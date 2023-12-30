package com.schm.schoolmanager.domain.student.service;

import com.schm.schoolmanager.domain.classes.service.ClassService;
import com.schm.schoolmanager.common.constant.GradeType;
import com.schm.schoolmanager.domain.student.dto.StudentReadResponse;
import com.schm.schoolmanager.domain.student.dto.StudentSaveRequest;
import com.schm.schoolmanager.domain.student.entity.Student;
import com.schm.schoolmanager.domain.student.mapper.StudentMapper;
import com.schm.schoolmanager.domain.student.repository.StudentRepository;
import com.schm.schoolmanager.domain.student.repository.StudentRepositoryImpl;
import com.schm.schoolmanager.domain.classes.entity.ClassRoom;
import com.schm.schoolmanager.common.exception.StudentMigrateException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final ClassService classService;
    private final StudentMapper mapper;
    private final StudentRepository repo;
    private final StudentRepositoryImpl QRepo;


    @Transactional
    public Student get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 학생입니다!"));
    }

    @Transactional
    public List<StudentReadResponse> getAll(List<Student> stds) {
        return mapper.toDtoList(stds);
    }


    @Transactional
    public List<StudentReadResponse> getAll(GradeType grade) {
        return QRepo.findAll(grade);
    }

    @Transactional
    public List<StudentReadResponse> getAll() {
        return QRepo.findAll();
    }

    @Transactional
    public List<StudentReadResponse> getStudentsByClass(Long id) {
        ClassRoom room = classService.get(id);
        return getAll(room.getStudents());
    }

    @Transactional
    public void save(StudentSaveRequest request) {
        Long roomId = request.getClassId();
        Student student = mapper.toEntity(request);
        if(roomId == null) {
            repo.save(student);
            return;
        }
        ClassRoom room = classService.get(roomId);
        student.setClass(room);
        repo.save(student);
    }

    @Transactional
    public void bulkSave(List<StudentSaveRequest> requests) {
        List<Long> roomIds = requests.stream()
                .filter(rq -> rq.getClassId() != null)
                .map(StudentSaveRequest::getClassId)
                .toList();
        List<ClassRoom> rooms = classService.getAllByIds(roomIds);

        repo.saveAll(
            requests.stream()
                    .map(rq -> {
                        Long roomId = rq.getClassId();
                        Student std = mapper.toEntity(rq);
                        if(roomId == null) {
                            return std;
                        }
                        ClassRoom room = rooms.stream().filter(r -> r.getId().equals(roomId)).findFirst().get();
                        std.setClass(room);
                        return std;
                    })
                    .toList()
        );
    }


    @Transactional
    public void migrateClass(Long studentId, Long classId) throws StudentMigrateException {
        Student std = get(studentId);
        if(std.isClassPresident()) throw new StudentMigrateException("기존 학급에서 새로운 반장 선출 후, 이동시킬 수 있습니다.");
        std.setClass(classService.get(classId));
    }

}
