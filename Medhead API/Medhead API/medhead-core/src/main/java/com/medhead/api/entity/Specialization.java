package com.medhead.api.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Component
public abstract class Specialization
{
    public enum specializationType { CARDIOLOGY, IMMUNOLOGY, NEUROPATHOLOGY }
    @Id
    private long id;
    private specializationType specialization;
    @ManyToMany
    private Set<Doctor> doctors;


    protected Specialization(final String name, final specializationType specialization)
    {
        this.specialization = specialization;
    }
}
