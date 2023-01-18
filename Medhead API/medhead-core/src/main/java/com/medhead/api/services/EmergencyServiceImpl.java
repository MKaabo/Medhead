package com.medhead.api.services;

import com.medhead.api.dao.EmergencyRepository;
import com.medhead.api.dao.entity.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service
public class EmergencyServiceImpl implements EmergencyService
{
    @Autowired
    public EmergencyRepository emergencyRepository;

    @Override
    public ArrayList<Emergency> findAllReservations()
    {
        return emergencyRepository.findAllEmergency();
    }

    @Override
    public Emergency findReservationByID(long id)
    {
        return emergencyRepository.findEmergencyByID(id);
    }

    @Override
    public void addEmergency(Emergency emergency) {
        emergencyRepository.addEmergency(emergency);
    }

    @Override
    public void removeEmergency(long id) {
        emergencyRepository.removeEmergency(id);
    }
}
