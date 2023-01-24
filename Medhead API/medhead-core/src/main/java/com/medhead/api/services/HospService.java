package com.medhead.api.services;

import com.medhead.api.dto.Hospital;
import org.springframework.stereotype.Service;

@Service
public interface HospService {

    // GET
    public Hospital findHospitalById(long id);
    public Hospital findHospitalByPosition(String pos);

    // POST
    public Hospital add(Hospital hospital);

    // DELETE
    public void deleteById(long id);
}
