package com.medhead.api.services;

import com.medhead.api.dto.Appointment;
import com.medhead.api.dto.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService
{
    public List<Doctor> findAll();
    public Doctor add(Doctor doctor);
    public void removeById(long id);
    public Doctor findDoctorById(long id);
    Doctor updateDoctor(long id, Doctor doctor);
    public List<Doctor> findByHospitalId(long id);
}
