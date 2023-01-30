package com.medhead.api.services;

import com.medhead.api.dao.EmergencyRepository;
import com.medhead.api.dao.entity.EmergencyEntity;
import com.medhead.api.dao.entity.PatientEntity;
import com.medhead.api.dto.*;
import com.medhead.api.mapper.EmergencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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
    private RestTemplate hospitalRestTemplate;
    @Autowired
    private RestTemplate patientRestTemplate;

    @Override
    public Emergency findEmergencyById(long id)
    {
        return this.emergencyMapper.toModel(emergencyRepository.findEmergencyById(id));
    }

    @Override
    public Emergency add(int patientId)
    {
        EmergencyEntity emergency = new EmergencyEntity(getPatient(patientId));
        List <Hospital> hospitals = getHospitalsList();
        return this.emergencyMapper.toModel(this.emergencyRepository.save(emergency));
    }

    @Override
    public void removeById(long id) {
        this.emergencyRepository.deleteById(id);
    }

    private PatientEntity getPatient(int patientId)
    {
        String patientRequest = "http://localhost/patient/" + patientId;
        return hospitalRestTemplate.getForObject(patientRequest, PatientEntity.class);
    }

    private List<Hospital> getHospitalsList()
    {
        String hospitalRequest = "http://localhost/hospital/";
        Hospital[] hospitals = hospitalRestTemplate.getForObject(hospitalRequest, Hospital[].class);
        return Arrays.asList(hospitals);
    }
/*
    private Hospital getClosestHospital(final String patientPosition)
    {
        final int patientIndex = 0;
        DirectionRequest request = new DirectionRequest();
        //  durations[i][j] -> travel distance from the ith source to the jth durations
        // we are only interested in the durations from durations[0] (our patient position)
        List<Float> distancesFromPatient = durations.get(patientIndex);

        // Sort it in ascending order (closest to farthest)
        Collections.sort(distancesFromPatient);

        List <Hospital> closestHospitals = new ArrayList<>();
        int hospitalIndex;
        // Create a list of hospitals sorted from closest to farthest
        for (Float f : distancesFromPatient)
        {
            hospitalIndex = durations.indexOf(f);
            closestHospitals.add(emergency.getHospital().get(hospitalIndex));
        }

        // Remove hospitals with no beds or lacking required specialization
        for (Hospital hospital : closestHospitals)
        {
            if (hospital == null);
            else if (hospital.getBedsAvailable() == 0);
            else if (!hospital.getSpecializations().contains(emergency.getSpecializationNeeded()));
            else return hospital;
        }
        return null;
    }

 */
}

