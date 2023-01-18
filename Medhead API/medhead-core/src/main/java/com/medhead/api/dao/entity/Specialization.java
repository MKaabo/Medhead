package com.medhead.api.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "specialization")
public class Specialization
{
    public enum specializationType { CARDIOLOGY, IMMUNOLOGY, NEUROPATHOLOGY }
    @Id
    private long id;
    private specializationType specialization;
    @ManyToMany
    private Set<Doctor> doctors;
}
