package com.medhead.api.services;

import com.medhead.api.dao.entity.PatientEntity;
import com.medhead.api.dto.Patient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

@Service
public interface PatientService
{
    public Patient findPatientById(long id);

    // POST
    public Patient add(Patient patient);

    // DELETE
    public void deleteById(long id);
}
