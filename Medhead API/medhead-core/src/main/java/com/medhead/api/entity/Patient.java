package com.medhead.api.entity;

import lombok.Getter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Getter
public class Patient
{
    @Id
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String infoField;
    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty
    private String phone;

    @ManyToOne
    @JoinColumn(name = "id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup("patient")
    private Set <Emergency> emergencies;

    @ManyToOne
    @JoinColumn(name = "id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup("patient")
    private Set<Appointment> appointments;

}
