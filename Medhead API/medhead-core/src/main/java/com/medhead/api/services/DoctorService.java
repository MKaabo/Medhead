package com.medhead.api.services;

import com.medhead.api.dao.entity.DoctorEntity;
import com.medhead.api.dto.Doctor;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService
{
    public void add(Doctor doctor);
    public void removeById(long id);
    public Doctor findDoctorById(long id);
}
