package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@jakarta.persistence.Entity
@NoArgsConstructor
@Data
@Table(name = "doctor")
public class DoctorEntity extends Entity
{

    private String name;
    @ManyToMany
    private Set<SpecializationEntity> specializations;

    @OneToMany (mappedBy = "doctor", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set <AppointmentEntity> appointments;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="hospital_id", referencedColumnName="id")
    @NotEmpty
    private HospitalEntity hospital;
    private boolean isAvailable;

}
