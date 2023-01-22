package com.medhead.api.dao.entity;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@jakarta.persistence.Entity
@NoArgsConstructor
@Data
@Table(name = "specialization")
public class SpecializationEntity extends com.medhead.api.dao.entity.Entity
{
    public enum specializationType { CARDIOLOGY, IMMUNOLOGY, NEUROPATHOLOGY }
    private specializationType specialization;
    @ManyToMany
    private Set<DoctorEntity> doctors;
}
