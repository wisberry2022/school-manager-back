package com.schm.schoolmanager.domain.staff_manage.teacher.entity;

import com.schm.schoolmanager.domain.fund_info.position.entity.Position;
import com.schm.schoolmanager.domain.fund_info.qualified.entity.Qualified;
import com.schm.schoolmanager.domain.subject.entity.Subject;
import jakarta.persistence.*;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "qualified_id")
    private Qualified qualified;

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setQualified(Qualified qualified) {
        this.qualified = qualified;
    }

}
