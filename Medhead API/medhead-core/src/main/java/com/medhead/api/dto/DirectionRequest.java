package com.medhead.api.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class DirectionRequest
{
    private String code;
    private List<Destination> destinations;
    private List <List<Float>> durations;
    private List<Destination> sources;
}