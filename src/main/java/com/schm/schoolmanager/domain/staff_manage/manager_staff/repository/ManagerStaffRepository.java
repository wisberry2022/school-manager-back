package com.schm.schoolmanager.domain.staff_manage.manager_staff.repository;

import com.schm.schoolmanager.domain.staff_manage.manager_staff.entity.ManagerStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerStaffRepository extends JpaRepository<ManagerStaff, Long> {

}
