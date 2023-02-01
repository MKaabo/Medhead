package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.*;

import java.util.Set;

@jakarta.persistence.Entity
@NoArgsConstructor
@Getter
@Setter
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

}
