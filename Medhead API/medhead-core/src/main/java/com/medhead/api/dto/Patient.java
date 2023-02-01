package com.medhead.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Patient extends Model
{
    private String name;
    @NotEmpty
    private String info;
    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    private String position;
    private Specialization specialization;
    private List<Emergency> emergencies;
    private List<Emergency> appointments;
}
