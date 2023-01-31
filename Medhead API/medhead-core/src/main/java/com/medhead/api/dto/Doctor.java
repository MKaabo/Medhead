package com.medhead.api.dto;

import com.medhead.api.dao.entity.AppointmentEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class Doctor extends Model
{
    private String name;

    private Specialization specialization;

    private Set <AppointmentEntity> appointments;

    private boolean isAvailable;
}
