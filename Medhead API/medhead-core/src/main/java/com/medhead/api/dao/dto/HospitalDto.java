package com.medhead.api.dao.dto;

import com.medhead.api.dto.Specialization;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public class HospitalDto
{
    private String name;
    @NotNull
    private String position;
    @Positive
    private int bedsAvailable;
    private Set<Specialization> specializations;
}
