package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@jakarta.persistence.Entity
@NoArgsConstructor
@Table(name = "emergency")
public class EmergencyEntity extends Entity
{
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="patientId", referencedColumnName="id")
    private PatientEntity patient;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="hospitalId", referencedColumnName="id")
    private HospitalEntity hospital;

}
