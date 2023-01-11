package com.medhead.api.services;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.dao.entity.Appointment;
import com.medhead.api.dao.entity.Hospital;
import com.medhead.api.dao.entity.Specialization;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
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
