package com.medhead.api.services;
import com.medhead.api.dto.Emergency;
import com.medhead.api.dto.Hospital;
import com.medhead.api.dto.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmergencyService
{
    public Emergency findEmergencyById(long id);

    public Emergency add(Patient patient, List<Hospital> hospitals);

    public void removeById(long id);
}
