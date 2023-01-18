package com.medhead.api.services;

import com.medhead.api.dao.PatientRepository;
import com.medhead.api.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

@Service
public class PatientServiceImpl implements PatientService
{
    @Autowired
    public PatientRepository patientRepository;

    @Override
    public ArrayList<Patient> findAllPatients() {
        return patientRepository.findAllPatients();
    }

    @Override
    public Patient findPatientByID(long id) {
        return patientRepository.findPatientByID(id);
    }

    @Override
    public void postDocument(File file)
    {

    }

    @Override
    public void updateInfo(String infoUpdate, long idInfo)
    {

    }

    @Override
    public void addPatient(Patient patient) {
        patientRepository.addPatient(patient);
    }

    @Override
    public void removePatient(long id)
    {
        patientRepository.removePatient(id);
    }
}
