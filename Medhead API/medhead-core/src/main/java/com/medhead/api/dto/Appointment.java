package com.medhead.api.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Appointment extends Model
{
    private Doctor doctor;
    private Patient patient;
    private Date date;
}
