package com.medhead.api.services;

import com.medhead.api.dto.Hospital;
import com.medhead.api.dto.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService
{
    public Patient findPatientById(long id);
    public List<Patient> findAll();
    public Patient add(Patient patient);
    Patient updatePatient(long id, Patient patient);
    public void deleteById(long id);
}
