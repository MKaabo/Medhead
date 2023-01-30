package com.medhead.api.services;

import com.medhead.api.dto.Hospital;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HospitalService {

    // GET
    public Hospital findHospitalById(long id);

    public List<Hospital> findAll();
    // POST
    public Hospital add(Hospital hospital);

    // DELETE
    public void deleteById(long id);
}
