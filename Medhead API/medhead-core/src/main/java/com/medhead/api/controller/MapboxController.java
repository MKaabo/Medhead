package com.medhead.api.controller;

import Util.MapboxUtil;
import com.medhead.api.dto.Destination;
import com.medhead.api.dto.DirectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class MapboxController {
    @Autowired
    private RestTemplate restTemplate;

    private static String from = "43.499034,5.510685";
    private static String centreHospitalierAix = "43.535123,5.442709";
    private static String centreHospitalierMontPerrin = "43.520810,5.439959";

    @RequestMapping(value = "/directions")
    public String getDirectionsTest()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<String> directions = new ArrayList<>();
        directions.add(centreHospitalierAix);
        directions.add(centreHospitalierMontPerrin);
        String mapboxQuery = MapboxUtil.getDirectionMatrix(from, directions);
        DirectionRequest direction = null;
        try
        {
             direction = restTemplate.getForObject(mapboxQuery, DirectionRequest.class);
        }
        catch (HttpClientErrorException e)
        {
            System.err.println(e.getStatusCode());
            System.err.println(e.getResponseBodyAsString());
            System.err.print(e.getMessage());
        }
        if (direction != null)
            System.out.println("ok");
        return restTemplate.exchange(mapboxQuery, HttpMethod.GET, entity, String.class).getBody();
    }
}