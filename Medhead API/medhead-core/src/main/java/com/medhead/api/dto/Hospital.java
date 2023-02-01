package com.medhead.api.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class Hospital extends Model
{
    @NotNull
    private String position;
    @Positive
    private int bedsAvailable;
    private List<Doctor> doctors;
    private List<Emergency> emergencies;
}
