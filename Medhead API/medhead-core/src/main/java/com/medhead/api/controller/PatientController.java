package com.medhead.api.controller;

import com.medhead.api.dao.entity.Patient;
import com.medhead.api.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public void add(Patient patient) {
        patientService.addPatient(patient);
    }

    @GetMapping("/findAll")
    public ArrayList<Patient> getAllPatients() {
        return patientService.findAllPatients();
    }

    @GetMapping("/findById/{id}")
    public Patient getPatientByID(@PathVariable long id) {
        return patientService.findPatientByID(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id) {
        patientService.removePatient(id);
    }
}
