package com.medhead.api.services;

import com.medhead.api.entity.Emergency;

import java.util.ArrayList;

public interface ReservationService
{
    public ArrayList<Emergency> findAllReservations();

    public Emergency findReservationByID(long id);

    public void addEmergency(Emergency emergency);

    public void removeEmergency(long id);
}
