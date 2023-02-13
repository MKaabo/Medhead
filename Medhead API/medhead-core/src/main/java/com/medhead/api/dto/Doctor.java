package com.medhead.api.dto;

import com.medhead.api.dao.entity.AppointmentEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Doctor extends Model
{
    private String name;
    private Specialization specialization;
    private boolean isAvailable;
    private Hospital hospital;
    public Doctor(String name, Hospital hospital)
    {
        this.name = name;
        this.hospital = hospital;
    }
}
