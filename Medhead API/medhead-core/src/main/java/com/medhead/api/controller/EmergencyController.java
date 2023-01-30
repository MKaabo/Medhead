package com.medhead.api.controller;

import com.medhead.api.dao.entity.EmergencyEntity;
import com.medhead.api.dto.Appointment;
import com.medhead.api.dto.Emergency;
import com.medhead.api.dto.Hospital;
import com.medhead.api.dto.Patient;
import com.medhead.api.services.EmergencyService;
import com.medhead.api.services.HospitalService;
import com.medhead.api.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/emergency", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class EmergencyController
{
    @Autowired
    private EmergencyService emergencyService;
    @Autowired
    private PatientService patientService;

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public void add(@RequestParam int patientId)
    {
        Patient patient = patientService.findPatientById(patientId);
        List <Hospital> hospitals = hospitalService.findAll();
        this.emergencyService.add(patient, hospitals);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id)
    {
        this.emergencyService.removeById(id);
    }

    private Emergency emergency;
}
