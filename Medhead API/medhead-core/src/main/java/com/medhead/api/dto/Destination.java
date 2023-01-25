package com.medhead.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class Destination
{
    private float distance;
    private String name;
    private Location location;
}
