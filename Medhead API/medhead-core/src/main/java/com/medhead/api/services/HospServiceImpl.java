package com.medhead.api.services;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.dao.HospitalRepository;
import com.medhead.api.dto.Hospital;
import com.medhead.api.mapper.HospitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HospServiceImpl implements HospService {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Qualifier("hospitalMapperImpl")
    @Autowired
    private HospitalMapper hospitalMapper;
    @Override
    public Hospital findHospitalById(long id) {
        return this.hospitalMapper.toModel(hospitalRepository.findHospitalById(id));
    }
    @Override
    public Hospital findHospitalByPosition(String pos)
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
