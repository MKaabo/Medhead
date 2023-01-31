package com.medhead.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Hospital extends Model
{
    private String name;
    @NotNull
    private String position;
    @Positive
    private int bedsAvailable;
    private List<Doctor> doctors;
}
