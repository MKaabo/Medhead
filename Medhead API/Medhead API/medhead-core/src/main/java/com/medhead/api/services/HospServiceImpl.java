package com.medhead.api.services;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.dao.HospitalRepository;
import com.medhead.api.entity.Appointment;
import com.medhead.api.entity.Hospital;
import com.medhead.api.entity.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class HospServiceImpl implements HospService {
    @Autowired
    public HospitalRepository hospitalRepository;

    @Override
    public ArrayList<Hospital> findAllHospitals() {
        return (ArrayList<Hospital>) hospitalRepository.findAllHospital();
    }

    @Override
    public Hospital findHospitalByID(long id) {
        return hospitalRepository.findHospitalByID(id);
    }

    @Override
    public Hospital getClosestAvailableHospital(Point position)
    {
        return null; // TODO
    }

    @Override
    public Appointment getAppointment(Point position, Specialization spec) {
        return null;
    }

    @Override
    public void addHospital(Hospital hospital) {
        hospitalRepository.addHospital(hospital);
    }

    @Override
    public void removeHospital(long id)
    {
        hospitalRepository.removeHospital(id);
    }
}
