package com.medhead.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public class Hospital extends Model
{
    private String name;
    @NotNull
    private String position;

    @Positive
    private int bedsAvailable;

    private Set<Specialization> specializations;
}
