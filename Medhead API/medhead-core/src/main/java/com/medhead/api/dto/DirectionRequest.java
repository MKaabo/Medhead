package com.medhead.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
// https://docs.mapbox.com/api/navigation/matrix/
public class DirectionRequest
{
    private String code = "";
    private List<Destination> destinations = new ArrayList<>();
    private List <List<Float>> durations = new ArrayList<>();
    private List<Destination> sources = new ArrayList<>();
}