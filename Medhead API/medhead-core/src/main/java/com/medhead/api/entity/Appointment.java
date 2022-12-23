package com.medhead.api.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
public class Appointment
{
    @Id
    private long id;

    @OneToMany(mappedBy = "appointments", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Doctor doctor;

    @OneToMany (mappedBy = "appointments", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @NotEmpty
    private Patient patient;

    @Future
    private Date date;

    public Appointment() {
    }

    public Appointment(Doctor doctor, Patient patient, Date date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }

}
