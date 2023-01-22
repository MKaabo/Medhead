package com.medhead.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Emergency extends Model {

    private Patient patient;

    private Hospital hospital;

}
