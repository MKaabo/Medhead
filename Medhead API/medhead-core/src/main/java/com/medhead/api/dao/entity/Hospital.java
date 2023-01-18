package com.medhead.api.dao.entity;

import com.mapbox.services.commons.geojson.Point;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "hospital")
public class Hospital
{
    @Id
    private long id;
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    private String position;
    @Positive
    @NotNull
    private int totalBeds;
    @Positive
    private int bedsAvailable;
    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Doctor> doctors;
    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Emergency> emergencies;
}
