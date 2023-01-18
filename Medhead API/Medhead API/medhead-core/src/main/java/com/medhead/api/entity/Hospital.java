package com.medhead.api.entity;

import com.mapbox.services.commons.geojson.Point;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Hospital
{
    @Id
    private long id;
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    private Point position;
    @Positive
    @NotNull
    private int totalBeds;
    @Positive
    private int bedsAvailable;

    @ManyToOne
    @JoinColumn(name = "id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup("hospitals")
    private Set<Doctor> doctors;
    private Set<Emergency> emergencies;
    public Hospital(String name, Point position, int totalBeds) {
        this.name = name;
        this.position = position;
        this.totalBeds = totalBeds;
        this.bedsAvailable = totalBeds;
    }
}
