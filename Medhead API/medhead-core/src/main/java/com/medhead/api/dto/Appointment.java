package com.medhead.api.dto;

import com.medhead.api.dao.entity.DoctorEntity;
import com.medhead.api.dao.entity.PatientEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Appointment extends Model
{
    private DoctorEntity doctor;
    private PatientEntity patient;
    private Date date;
}
