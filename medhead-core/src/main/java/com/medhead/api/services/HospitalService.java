package com.medhead.api.services;

import com.medhead.api.dto.Appointment;
import com.medhead.api.dto.Hospital;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HospitalService
{
    public Hospital findHospitalById(long id);
    public List<Hospital> findAll();
    public Hospital add(Hospital hospital);
    public void deleteById(long id);
    Hospital updateHospital(long id, Hospital hospital);
}
