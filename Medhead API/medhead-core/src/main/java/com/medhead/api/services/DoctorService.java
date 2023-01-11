package com.medhead.api.services;

import com.medhead.api.dao.entity.Doctor;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService
{
    public void addDoctor(Doctor doctor);
    public void removeDoctor(long id);
    public Doctor findDoctorById(long id);
}
