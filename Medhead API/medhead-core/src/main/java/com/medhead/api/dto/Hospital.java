package com.medhead.api.dto;

import com.medhead.api.dao.entity.SpecializationEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public class Hospital extends Model
{
    private String name;
    @NotNull
    private String position;

    @Positive
    private int beds_available;

    private Set<SpecializationEntity> specializations;
}
