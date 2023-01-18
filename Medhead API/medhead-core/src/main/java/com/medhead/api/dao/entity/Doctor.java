package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "doctor")
public class Doctor
{
    @Id
    private long id;
    private String name;
    @ManyToMany
    private Set<Specialization> specializations;
    @OneToMany (mappedBy = "doctor", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set <Appointment> appointments;
    @ManyToOne
    @NotEmpty
    private Hospital hospital;

    private boolean isAvailable;

}
