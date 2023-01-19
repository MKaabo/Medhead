package com.medhead.api.services;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.dao.HospitalRepository;
import com.medhead.api.dao.entity.AppointmentEntity;
import com.medhead.api.dao.entity.HospitalEntity;
import com.medhead.api.dao.entity.SpecializationEntity;
import com.medhead.api.dto.Hospital;
import com.medhead.api.dto.Specialization;
import com.medhead.api.mapper.HospitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HospServiceImpl implements HospService {
    @Autowired
    private HospitalRepository hospitalRepository;

    private HospitalMapper hospitalMapper;
    @Override
    public Hospital findHospitalById(long id) {
        return this.hospitalMapper.toModel(hospitalRepository.findHospitalByID(id));
    }

    @Override
    public Hospital getClosestAvailableHospital(Point position)
    {
        return null; // TODO
    }
    @Override
    public Hospital add(Hospital hospital) {
        return this.hospitalMapper.toModel(this.hospitalRepository.save(this.hospitalMapper.toEntity(hospital)));
    }
    @Override
    public void deleteById(long id)
    {
        this.hospitalRepository.deleteById(id);
    }
}
