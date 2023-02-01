package com.medhead.api.dao.entity;

import com.medhead.api.dto.Specialization;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@jakarta.persistence.Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "doctor")
public class DoctorEntity extends Entity {
    private String name;
    @Enumerated
    private Specialization specialization;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    @NotEmpty
    private HospitalEntity hospital;
    @Column(name = "is_available")
    private boolean isAvailable;

}
