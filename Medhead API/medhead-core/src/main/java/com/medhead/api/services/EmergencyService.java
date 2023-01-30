package com.medhead.api.services;
import com.medhead.api.dto.Emergency;
import org.springframework.stereotype.Service;

@Service
public interface EmergencyService
{
    public Emergency findEmergencyById(long id);

    public Emergency add(int patientId);

    public void removeById(long id);
}
