package com.medhead.api.dao.entity;

import com.medhead.api.dto.Specialization;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.util.List;

@jakarta.persistence.Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "patient")
public class PatientEntity extends Entity
{
    @NotEmpty
    private String name;
    @NotEmpty
    private String info;
    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty
    private String phone;

    private String position;

    private Specialization specialization;

}
