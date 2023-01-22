package com.medhead.api.dto;

import com.medhead.api.dao.entity.AppointmentEntity;
import com.medhead.api.dao.entity.HospitalEntity;
import com.medhead.api.dao.entity.SpecializationEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Setter
@Getter
public class Doctor extends Model
{
    private String name;

    private Set<SpecializationEntity> specializations;

    private Set <AppointmentEntity> appointments;

    private HospitalEntity hospital;

    private boolean is_available;
}
