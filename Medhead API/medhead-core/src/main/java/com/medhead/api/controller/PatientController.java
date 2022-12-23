package com.medhead.api.controller;

import com.medhead.api.entity.Patient;
import com.medhead.api.services.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientServiceImpl patientServiceImpl;

    @PostMapping("/add")
    public void add(Patient patient) {
        patientServiceImpl.addPatient(patient);
    }

    @GetMapping("/findAll")
    public ArrayList<Patient> getAllPatients() {
        return patientServiceImpl.findAllPatients();
    }

    @GetMapping("/findById/{id}")
    public Patient getPatientByID(@PathVariable long id) {
        return patientServiceImpl.findPatientByID(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id) {
        patientServiceImpl.removePatient(id);
    }
}
