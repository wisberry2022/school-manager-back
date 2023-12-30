package com.schm.schoolmanager.domain.fund_info.position.entity;

import com.schm.schoolmanager.domain.fund_info.position.dto.PositionReadResponse;
import com.schm.schoolmanager.domain.fund_info.position.dto.PositionSaveRequest;
import com.schm.schoolmanager.domain.fund_info.position.dto.PositionUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PositionMap> mapList = new ArrayList<>();

    public void addItem(Position p, Long id) {
        PositionMap pMap = PositionMap.mapping(p, id);
        mapList.add(pMap);
    }

    public void removeItem(Long staffId, Long pId) {
        Long mapId = mapList.stream()
                .filter(l -> l.getStaffId().equals(staffId) &&
                        l.getPosition().getId().equals(pId))
                .findFirst()
                .get()
                .getId();
        mapList.removeIf(pm -> pm.getId().equals(mapId));
    }

    public static Position of(PositionSaveRequest request) {
        return Position
                .builder()
                .code(request.getCode())
                .name(request.getName())
                .build();
    }

    public PositionReadResponse toDto() {
        return PositionReadResponse
                .builder()
                .id(this.id)
                .code(this.code)
                .name(this.name)
                .build();
    }

    public void update(PositionUpdateRequest request) {
        Optional.of(request.getCode()).ifPresent(code -> this.code = code);
        Optional.of(request.getName()).ifPresent(name -> this.name = name);
    }

}
