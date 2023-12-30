package com.schm.schoolmanager.domain.staff_manage.manager_staff.service;

import com.schm.schoolmanager.common.util.RepositoryUtil;
import com.schm.schoolmanager.domain.staff_manage.manager_staff.entity.ManagerStaff;
import com.schm.schoolmanager.domain.staff_manage.manager_staff.repository.ManagerStaffRepository;
import com.schm.schoolmanager.domain.fund_info.qualified.service.QualifiedService;
import com.schm.schoolmanager.domain.staff_manage.manager_staff.dto.ManagerStaffCreateRequest;
import com.schm.schoolmanager.domain.staff_manage.manager_staff.dto.ManagerStaffReadResponse;
import com.schm.schoolmanager.domain.fund_info.qualified.entity.Qualified;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerStaffService {

    private final ManagerStaffRepository repo;
    private final QualifiedService qualifiedService;

    public List<ManagerStaffReadResponse> getAll() {
        return RepositoryUtil.getAll(repo, entity -> entity.toDto());
    }

    public void save(ManagerStaffCreateRequest request) {
        Qualified qualified = qualifiedService.get(request.getQualified());
        repo.save(ManagerStaff.of(request, qualified));
    }

    public void bulkSave(List<ManagerStaffCreateRequest> requests) {
        List<Qualified> list = qualifiedService.getAllEntity();

        repo.saveAll(
            requests.stream().map(request -> {
                Qualified qualified = list.stream().filter(q -> q.getQualifiedType().equals(request.getQualified())).findFirst().get();
                return ManagerStaff.of(request, qualified);
            }).toList()
        );

    }

}
