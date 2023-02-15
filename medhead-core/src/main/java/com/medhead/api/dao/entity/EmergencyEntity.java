package com.medhead.api.dao.entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="patientId", referencedColumnName="id")
    @NotNull
    private PatientEntity patient;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="hospitalId", referencedColumnName="id")
    private HospitalEntity hospital;
}
