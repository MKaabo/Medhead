package com.medhead.api.controller;

import com.medhead.api.dao.entity.EmergencyEntity;
import com.medhead.api.dto.*;
import com.medhead.api.services.DoctorService;
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
    private DoctorService doctorService;
    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public List<Emergency> getEmergencies()
    {
        return this.emergencyService.findAll();
    }
    @PostMapping
    public void add(@RequestParam int patientId)
    {
        Patient patient = this.patientService.findPatientById(patientId);
        List <Hospital> hospitals = this.hospitalService.findAll();
        List <Doctor> doctors;
        for (Hospital hospital : hospitals)
        {
            doctors = this.doctorService.findByHospitalId(hospital.getId());
            hospital.setDoctors(doctors);
        }
        this.emergencyService.add(patient, hospitals);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id)
    {
        this.emergencyService.removeById(id);
    }

}
