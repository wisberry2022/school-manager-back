package com.schm.schoolmanager.domain.fund_info.task.repository;

import com.schm.schoolmanager.domain.fund_info.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
