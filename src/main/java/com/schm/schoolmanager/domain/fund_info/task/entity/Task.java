package com.schm.schoolmanager.domain.fund_info.task.entity;

import com.schm.schoolmanager.domain.fund_info.task.dto.TaskCreateRequest;
import com.schm.schoolmanager.domain.fund_info.task.dto.TaskReadResponse;
import com.schm.schoolmanager.domain.fund_info.task.dto.TaskUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String name;

    public static Task of(TaskCreateRequest request) {
        return Task
                .builder()
                .code(request.getCode())
                .name(request.getName())
                .build();
    }

    public TaskReadResponse toDto() {
        return TaskReadResponse
                .builder()
                .id(this.id)
                .code(this.code)
                .name(this.name)
                .build();
    }

    public void update(TaskUpdateRequest request) {
        this.name = request.getName();
        this.code = request.getCode();
    }

}
