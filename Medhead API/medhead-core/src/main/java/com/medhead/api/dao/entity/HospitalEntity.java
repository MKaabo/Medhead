package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.Set;

@jakarta.persistence.Entity
@NoArgsConstructor
@Data
@Table(name = "hospital")
public class HospitalEntity extends Entity
{
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    private String position;
    @Positive
    @NotNull
    @Column(name="total_beds")
    private int totalBeds;
    @Positive
    @Column(name="beds_available")
    private int bedsAvailable;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="hospital")
    private Set<DoctorEntity> doctors;
    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<EmergencyEntity> emergencies;
}
