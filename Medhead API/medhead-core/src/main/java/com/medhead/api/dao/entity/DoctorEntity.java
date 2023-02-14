package com.medhead.api.dao.entity;

import com.medhead.api.dto.Specialization;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@jakarta.persistence.Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "doctor")
public class DoctorEntity extends Entity
{
    @NotEmpty
    private String name;
    @Enumerated
    private Specialization specialization;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    @NotNull
    private HospitalEntity hospital;
    @Column(name = "is_available")
    private boolean isAvailable;

}
