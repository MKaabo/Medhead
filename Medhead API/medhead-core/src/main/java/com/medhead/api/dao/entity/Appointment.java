package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name = "appointment")
public class Appointment
{
    @Id
    private long id;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    @Future
    private Date date;
}
