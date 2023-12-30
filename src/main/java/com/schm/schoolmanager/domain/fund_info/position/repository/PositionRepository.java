package com.schm.schoolmanager.domain.fund_info.position.repository;

import com.schm.schoolmanager.domain.fund_info.position.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findByCode(String code);

}
