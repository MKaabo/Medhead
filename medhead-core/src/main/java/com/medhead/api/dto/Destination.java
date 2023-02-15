package com.medhead.api.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Destination
{
    private float distance;
    private String name;
    private List<Float> location;
}
