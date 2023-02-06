package com.medhead.api.dto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Hospital extends Model
{
    private static long idCount = 0;
    private String name;
    private String position;
    private int totalBeds;
    private int bedsAvailable;
    private List<Doctor> doctors = new ArrayList<>();
    private List<Emergency> emergencies;

    public Hospital(String name, String position, int totalBeds, int bedsAvailable)
    {
        this.id = idCount++;
        this.name = name;
        this.position = position;
        this.totalBeds = totalBeds;
        this.bedsAvailable = bedsAvailable;
    }
}
