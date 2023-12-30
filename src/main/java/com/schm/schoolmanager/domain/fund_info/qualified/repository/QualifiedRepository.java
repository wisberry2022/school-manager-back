package com.schm.schoolmanager.domain.fund_info.qualified.repository;

import com.schm.schoolmanager.common.constant.QualifiedType;
import com.schm.schoolmanager.domain.fund_info.qualified.entity.Qualified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualifiedRepository extends JpaRepository<Qualified, Long> {

    Optional<Qualified> findByCode(String code);

    Optional<Qualified> findByQualifiedType(QualifiedType type);

}
