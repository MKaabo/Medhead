package com.medhead.api.services;

import Util.MapboxUtil;
import com.medhead.api.dao.DoctorRepository;
import com.medhead.api.dao.EmergencyRepository;
import com.medhead.api.dao.entity.EmergencyEntity;
import com.medhead.api.dto.*;
import com.medhead.api.mapper.EmergencyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

@Transactional
@Service
public class EmergencyServiceImpl implements EmergencyService
{
    private static Logger logger = LoggerFactory.getLogger(EmergencyServiceImpl.class);

    @Autowired
    private EmergencyRepository emergencyRepository;
    @Autowired
    private EmergencyMapper emergencyMapper;
    @Autowired
    private RestTemplate mapboxRestTemplate;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Emergency findEmergencyById(long id)
    {
        return this.emergencyMapper.toModel(emergencyRepository.findEmergencyById(id));
    }

    @Override
    public Emergency add(Patient patient, List<Hospital> hospitals)
    {
        Emergency emergency = new Emergency(patient);

        Hospital hospital = findClosestHospital(patient, hospitals);
        emergency.setHospital(hospital);
        return this.emergencyMapper.toModel(
                this.emergencyRepository.save(
                        this.emergencyMapper.toEntity(emergency)));
    }

    @Override
    public void removeById(long id) {
        this.emergencyRepository.deleteById(id);
    }

    @Override
    public List<Emergency> findAll() {
        List<EmergencyEntity> emergencyList = this.emergencyRepository.findAll();
        //filter all appointment after today
        return this.emergencyMapper.toModelList(
                emergencyList
                        .stream()
                        .collect(Collectors.toList())
        );
    }

    public Hospital findClosestHospital(Patient patient, List <Hospital> hospitals)
    {
        final int patientIndex = 0;
        List <DirectionRequest> directionsRequest = getMapboxDirections(patient, hospitals);
        // Remove all durations that represent travel from patient to patient
        for (DirectionRequest request : directionsRequest)
            request.getDurations().get(patientIndex).remove(patientIndex);
        // Set up the matrix
        List <List <Float>> durations = directionsRequest.get(0).getDurations();
        List <List <Float>> currentDirectionMatrix;

        // For each row of the matrix, add durations from other DirectionRequests
        for (int i = 1; i < directionsRequest.size(); i++)
        {
            currentDirectionMatrix = directionsRequest.get(i).getDurations();
            durations.get(patientIndex).addAll(currentDirectionMatrix.get(patientIndex));
        }

        //  durations[i][j] -> travel distance from the ith source to the jth durations
        // we are only interested in the durations from durations[0] (our patient position)
        List <Float> travelTimeFromPatient = new ArrayList<>(durations.get(patientIndex));
        if (travelTimeFromPatient.isEmpty())
        {
            logger.info("No hospitals were found for this emergency. Please contact our support.");
            return null;
        }

        travelTimeFromPatient.sort(nullsLast(comparing(Float::floatValue)));
        List <Hospital> closestHospitals = new ArrayList<> ();
        // Create a list of hospitals sorted from closest to farthest
        // index is the position in hospitals list corresponding to its travel time in durations
        int index;
        for (int i = 0;i < travelTimeFromPatient.size(); i++)
        {
            if (travelTimeFromPatient.get(i) == null)
                continue;
            index = durations.get(patientIndex).indexOf(travelTimeFromPatient.get(i));
            closestHospitals.add(hospitals.get(index));
        }


        List<Specialization> hospitalSpecializations;
        for (Hospital hospital : closestHospitals)
        {
            hospitalSpecializations = new ArrayList<>();
            // Get specializations
            for (Doctor doctor : hospital.getDoctors())
               hospitalSpecializations.add(doctor.getSpecialization());

            // Remove hospitals with no beds or lacking required specialization
            if (hospital != null && hospital.getBedsAvailable() > 0)
            {
                if (patient.getSpecialization() == null)
                {
                    logger.debug("Hospital " + hospital.getName() + " in position " + hospital.getPosition() + " chosen.");
                    return hospital;
                }
                else if (hospitalSpecializations.contains(patient.getSpecialization()))
                {
                    logger.debug("Hospital " + hospital.getName() + " in position " + hospital.getPosition() + " chosen for its specialization.");
                    return hospital;
                }
            }
        }
        logger.error("An error happened or no hospital were found for this emergency.");
        return null;
    }
    @RequestMapping(value = "/directions")
    public List <DirectionRequest> getMapboxDirections(Patient patient, List <Hospital> hospitals)
    {
        // Create list with hospitals coordinates
        final int MAX_DESTINATIONS = 10;
        int subListIndex = 0;
        List <String> hospitalsPosition = new ArrayList<>();
        for (Hospital hospital : hospitals)
            hospitalsPosition.add(hospital.getPosition());

        if (hospitals.size() > MAX_DESTINATIONS)
        {
            logger.debug("Multiple calls to MaxboxUtil will be done because the " +
                    " amount of destinations is greater than 10.");
            subListIndex = MAX_DESTINATIONS - 1;
        }
        else
            subListIndex = hospitals.size();

        List <DirectionRequest> directionsRequest = new ArrayList<>();
        String mapboxQuery;
        DirectionRequest directionRequest = null;
        int i = 0;
        // For each 10 possible hospitals, make a mapbox request
        while (true)
        {
            if (hospitalsPosition.size() == 1)
                mapboxQuery = MapboxUtil.getDirectionMatrix(patient.getPosition(), hospitalsPosition);
            else
                mapboxQuery = MapboxUtil.getDirectionMatrix(patient.getPosition(), hospitalsPosition.subList(i, subListIndex));
            directionRequest = getDirectionObject(mapboxQuery);
            if (directionRequest != null)
                directionsRequest.add(directionRequest);

            // Set i for the next 10 objects or exit
            if (subListIndex == hospitals.size())
                break;
            else
                i = subListIndex;
            if (subListIndex + MAX_DESTINATIONS < hospitals.size())
                subListIndex += MAX_DESTINATIONS - 1;
            else
                subListIndex = hospitals.size();
        }

        return directionsRequest;
    }

    private DirectionRequest getDirectionObject(final String mapboxQuery)
    {
        try
        {
            ResponseEntity<DirectionRequest> response = mapboxRestTemplate.getForEntity(mapboxQuery, DirectionRequest.class);
            logger.debug(response.getStatusCode().toString());
            if (response.getBody() == null)
                logger.error("The following mapbox query returned null. Please make sure it is correct.\n" + mapboxQuery);
            return response.getBody();
        }
        catch (HttpClientErrorException e)
        {
            logger.error(e.getStatusCode().toString());
            logger.error(e.getResponseBodyAsString());
            return null;
        }
    }
}

