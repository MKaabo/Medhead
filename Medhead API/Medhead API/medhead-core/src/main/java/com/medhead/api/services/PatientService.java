package com.medhead.api.services;

import com.medhead.api.entity.Patient;

import java.io.File;
import java.util.ArrayList;

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
