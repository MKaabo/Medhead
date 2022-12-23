package com.medhead.api.services;

import com.medhead.api.entity.Doctor;

public interface DoctorService
{
    public void addDoctor(Doctor doctor);
    public void removeDoctor(long id);
    public Doctor findDoctorById(long id);
}
