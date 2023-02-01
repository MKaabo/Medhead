package com.medhead.api.dao.entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@jakarta.persistence.Entity
@Table(name = "emergency")
public class EmergencyEntity extends Entity
{
    public EmergencyEntity(PatientEntity patient)
    {
        this.patient = patient;
    }

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="patientId", referencedColumnName="id")
    private PatientEntity patient;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="hospitalId", referencedColumnName="id")
    private HospitalEntity hospital;
}
