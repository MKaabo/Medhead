package com.medhead.api.services;

import com.medhead.api.dao.DoctorRepository;
import com.medhead.api.entity.Doctor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService
{
    public DoctorRepository doctorRepository;

    public Doctor findDoctorById(long id)
    {
        return doctorRepository.findDoctorByID(id);
    }

    @Override
    public void addDoctor(Doctor doctor)
    {
        doctorRepository.addDoctor(doctor);
    }

    @Override
    public void removeDoctor(long id) {
        doctorRepository.removeDoctor(id);
    }

}
