package com.medhead.api.services;

import Util.MapboxUtil;
import com.medhead.api.dao.EmergencyRepository;
import com.medhead.api.dto.*;
import com.medhead.api.mapper.EmergencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class EmergencyServiceImpl implements EmergencyService
{
    @Autowired
    private EmergencyRepository emergencyRepository;
    @Autowired
    private EmergencyMapper emergencyMapper;

    @Autowired
    private RestTemplate mapboxRestTemplate;

    @Override
    public Emergency findEmergencyById(long id)
    {
        return this.emergencyMapper.toModel(emergencyRepository.findEmergencyById(id));
    }

    @Override
    public Emergency add(Patient patient, List<Hospital> hospitals)
    {
        Emergency emergency = new Emergency();
        emergency.setPatient(patient);

        Hospital hospital = findClosestHospital(patient, hospitals);
        return emergency;
    }

    @Override
    public void removeById(long id) {
        this.emergencyRepository.deleteById(id);
    }

    private Hospital findClosestHospital(Patient patient, List <Hospital> hospitals)
    {
        final int patientIndex = 0;
        DirectionRequest directionRequest = getDirections(patient, hospitals);
        List<List<Float>> durations = directionRequest.getDurations();
        //  durations[i][j] -> travel distance from the ith source to the jth durations
        // we are only interested in the durations from durations[0] (our patient position)
        List<Float> distancesFromPatient = durations.get(patientIndex);

        // Sort it in ascending order (closest to farthest)
        Collections.sort(distancesFromPatient);

        List<Hospital> closestHospitals = new ArrayList<>();
        int hospitalIndex;
        // Create a list of hospitals sorted from closest to farthest
        for (Float f : distancesFromPatient)
            closestHospitals.add(hospitals.get(durations.indexOf(f)));

        // Remove hospitals with no beds or lacking required specialization
        for (Hospital hospital : closestHospitals)
        {
            if (hospital != null &&
                hospital.getBedsAvailable() != 0 &&
                hospital.getSpecializations().contains(patient.getSpecialization()))
                return hospital;
        }
        return null;
    }
    @RequestMapping(value = "/directions")
    public DirectionRequest getDirections(Patient patient, List <Hospital> hospitals)
    {
        List <String> hospitalsPosition = new ArrayList<>();
        for (Hospital hospital : hospitals)
            hospitalsPosition.add(hospital.getPosition());

        String mapboxQuery = MapboxUtil.getDirectionMatrix(patient.getPosition(), hospitalsPosition);
        DirectionRequest directionRequest = null;
        try
        {
            directionRequest = mapboxRestTemplate.getForObject(mapboxQuery, DirectionRequest.class);
        }
        catch (HttpClientErrorException e)
        {
            System.err.println(e.getStatusCode());
            System.err.println(e.getResponseBodyAsString());
            System.err.print(e.getMessage());
        }
        return directionRequest;
    }
}

