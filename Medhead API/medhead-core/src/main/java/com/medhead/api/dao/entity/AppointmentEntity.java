package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@jakarta.persistence.Entity
@NoArgsConstructor
@Getter
@Setter
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
