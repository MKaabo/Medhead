package com.medhead.api.services;

import com.medhead.api.dao.entity.Patient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

@Service
public interface PatientService
{
    // GET
    public ArrayList<Patient> findAllPatients();
    public Patient findPatientByID(long id);

    // POST
    public void addPatient(Patient patient);

    // PUT
    public void postDocument(File file);

    // Each personnal info in user profile should have an id
    public void updateInfo(String infoUpdate, long idInfo);

    // DELETE
    public void removePatient(long id);
}
