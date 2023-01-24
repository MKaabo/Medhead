package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<DoctorEntity> doctors;
    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<EmergencyEntity> emergencies;
}
