package com.schm.schoolmanager.domain.fund_info.task.service;

import com.schm.schoolmanager.common.util.RepositoryUtil;
import com.schm.schoolmanager.domain.fund_info.task.dto.TaskUpdateRequest;
import com.schm.schoolmanager.domain.fund_info.task.dto.TaskCreateRequest;
import com.schm.schoolmanager.domain.fund_info.task.dto.TaskReadResponse;
import com.schm.schoolmanager.domain.fund_info.task.entity.Task;
import com.schm.schoolmanager.domain.fund_info.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repo;

    public Task get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 업무입니다!"));
    }

    public List<TaskReadResponse> getAll() {
        return RepositoryUtil.getAll(repo, task -> task.toDto());
    }

    public void register(TaskCreateRequest request) {
        Task t = Task.of(request);
        repo.save(t);
    }

    public void bulkRegister(List<TaskCreateRequest> requests) {
        repo.saveAll(
            requests.stream()
                    .map(r -> Task.of(r))
                    .toList()
        );
    }

    public void update(TaskUpdateRequest request) {
        Task task = get(request.getId());
        task.update(request);
        repo.save(task);
    }

}
