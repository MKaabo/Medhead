package com.medhead.api.dao;

import com.medhead.api.dao.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public ArrayList <Patient> findAllPatients();

    public Patient findPatientByID(long id);

    public void addPatient(Patient patient);

    public void removePatient(long ids);
}
