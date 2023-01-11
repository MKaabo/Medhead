package com.medhead.api.services;

import com.medhead.api.dao.entity.Emergency;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface EmergencyService
{
    public ArrayList<Emergency> findAllReservations();

    public Emergency findReservationByID(long id);

    public void addEmergency(Emergency emergency);

    public void removeEmergency(long id);
}
