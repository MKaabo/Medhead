package com.medhead.api.services;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.dao.entity.AppointmentEntity;
import com.medhead.api.dao.entity.HospitalEntity;
import com.medhead.api.dao.entity.SpecializationEntity;
import com.medhead.api.dto.Appointment;
import com.medhead.api.dto.Hospital;
import com.medhead.api.dto.Specialization;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface HospService {

    // GET
    public Hospital findHospitalById(long id);
    public Hospital getClosestAvailableHospital(Point position);

    // POST
    public Hospital add(Hospital hospital);

    // DELETE
    public void deleteById(long id);
}
