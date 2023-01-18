package com.medhead.api.entity;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Doctor
{
    @Id
    //TODOAutoGenerate
    private long id;
    private String name;
    @ManyToMany
    private Set<Specialization> specializations;
    @ManyToOne
    private Set <Appointment> appointments;

    @OneToMany(mappedBy = "doctors", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @NotEmpty
    private Hospital hospital;

    public Doctor()
    {
    }


    public String getName() {
        return name;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public Hospital getHospital() {
        return hospital;
    }
}
