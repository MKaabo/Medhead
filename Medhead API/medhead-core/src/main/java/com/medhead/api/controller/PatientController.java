package com.medhead.api.controller;
import com.medhead.api.dto.Hospital;
import com.medhead.api.dto.Patient;
import com.medhead.api.exception.PatientNotFoundException;
import com.medhead.api.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        try
        {
            return patientService.findPatientById(id);
        }
        catch(PatientNotFoundException exc)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient Not Found", exc);
        }

    }
    @GetMapping
    public List<Patient> getPatients()
    {
        try
        {
            return this.patientService.findAll();
        }
        catch (PatientNotFoundException exc)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patients Not Found", exc);
        }
    }

    @PatchMapping("/{id}")
    public Patient updatePatient(@RequestParam long patientId, Patient patient)
    {
        return this.patientService.updatePatient(patientId, patient);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        patientService.deleteById(id);
    }
}
