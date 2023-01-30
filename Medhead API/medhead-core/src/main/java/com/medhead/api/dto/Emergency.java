package com.medhead.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Emergency extends Model
{
    private Patient patient;

    private Specialization specializationNeeded;

    private Hospital hospital;

}
