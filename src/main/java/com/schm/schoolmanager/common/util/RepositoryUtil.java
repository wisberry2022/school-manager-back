package com.schm.schoolmanager.common.util;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Function;

public class RepositoryUtil {

    public static <Repository, Entity, DTO> List<DTO> getAll(
        JpaRepository<Entity, Long> repo,
        Function<Entity, DTO> translationFunc
    ) {
        return repo.findAll().stream()
                .map(translationFunc)
                .toList();
    }

    public static <Repository, Entity, DTO> void saveAll(
        List<DTO> requests,
        JpaRepository<Entity, Long> repo,
        Function<DTO, Entity> translationFunc
    ) {
        repo.saveAll(
            requests.stream()
                    .map(translationFunc)
                    .toList()
        );
    }

}
