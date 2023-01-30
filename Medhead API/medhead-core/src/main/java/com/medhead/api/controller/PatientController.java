package com.medhead.api.controller;
import com.medhead.api.dto.Patient;
import com.medhead.api.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/patient", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public void add(@RequestBody Patient patient)
    {
        this.patientService.add(patient);
    }
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable long id) {
        return patientService.findPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        patientService.deleteById(id);
    }
}
