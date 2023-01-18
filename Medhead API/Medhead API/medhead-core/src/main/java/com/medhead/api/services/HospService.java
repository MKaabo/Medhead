package com.medhead.api.services;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.entity.Appointment;
import com.medhead.api.entity.Hospital;
import com.medhead.api.entity.Specialization;

import java.util.ArrayList;

public interface HospService {

    // GET
    public ArrayList <Hospital> findAllHospitals();
    public Hospital findHospitalByID(long id);
    public Hospital getClosestAvailableHospital(Point position);
    public Appointment getAppointment(Point position, Specialization spec);

    // POST
    public void addHospital(Hospital hospital);

    // DELETE
    public void removeHospital(long id);
}
