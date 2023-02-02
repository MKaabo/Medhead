package com.medhead.api.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class Hospital extends Model
{
    private String name;
    private String position;
    private int totalBeds;
    private int bedsAvailable;
    private List<Doctor> doctors;
    private List<Emergency> emergencies;

    public Hospital(String name, String position, int totalBeds, int bedsAvailable)
    {
        this.name = name;
        this.position = position;
        this.totalBeds = totalBeds;
        this.bedsAvailable = bedsAvailable;
    }
}
