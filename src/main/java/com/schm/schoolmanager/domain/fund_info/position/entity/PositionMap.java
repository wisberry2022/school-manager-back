package com.schm.schoolmanager.domain.fund_info.position.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PositionMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private Long staffId;

    public static PositionMap mapping(Position p, Long id) {
        return PositionMap
                .builder()
                .position(p)
                .staffId(id)
                .build();
    }

}
