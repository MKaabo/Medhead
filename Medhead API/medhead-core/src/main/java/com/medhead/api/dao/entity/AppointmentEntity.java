package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@jakarta.persistence.Entity
@NoArgsConstructor
@Data
@Table(name = "appointment")
public class AppointmentEntity extends Entity {
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="doctorId", referencedColumnName="id")
    private DoctorEntity doctor;
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="patientId", referencedColumnName="id")
    private PatientEntity patient;
    @Future
    private Date  date;
}
