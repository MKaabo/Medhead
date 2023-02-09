package com.medhead.api.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Patient extends Model
{
    private String name;
    private String info;
    private int age;
    private String position;
    private Specialization specialization;
    private List<Emergency> emergencies;
    private List<Appointment> appointments;

    public Patient(String name, int age, String position)
    {
        this.name = name;
        this.age = age;
        this.position = position;
    }
}
