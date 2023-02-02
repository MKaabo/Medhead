package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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
    @NotNull
    @Column(name="beds_available")
    private int bedsAvailable;
    @AssertTrue
    private boolean isLessOrEqualsBedThanTotal()
    {
        return this.bedsAvailable <= this.totalBeds;
    }

    @AssertTrue
    private boolean isCoordinate()
    {
        return this.position.contains(";");
    }

}
