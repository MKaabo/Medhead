package com.medhead.api.dto;

import com.medhead.api.dao.entity.AppointmentEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Doctor extends Model
{
    private String name;
    private Specialization specialization;
    private List<AppointmentEntity> appointments;
    private boolean isAvailable;
    private Hospital hospital;
}
