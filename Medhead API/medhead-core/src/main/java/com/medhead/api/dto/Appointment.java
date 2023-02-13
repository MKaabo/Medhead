package com.medhead.api.dto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class Appointment extends Model
{
    private Doctor doctor;
    private Patient patient;
    private Date date;

    public Appointment(Doctor doctor, Patient patient, Date date)
    {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }
}
