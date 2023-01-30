package com.medhead.api.services;

import com.medhead.api.dao.HospitalRepository;
import com.medhead.api.dao.entity.DoctorEntity;
import com.medhead.api.dao.entity.HospitalEntity;
import com.medhead.api.dto.Hospital;
import com.medhead.api.mapper.HospitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Qualifier("hospitalMapperImpl")
    @Autowired
    private HospitalMapper hospitalMapper;
    @Override
    public Hospital findHospitalById(long id)
    {
        return this.hospitalMapper.toModel(hospitalRepository.findHospitalById(id));
    }

    @Override
    public List<Hospital> findAll()
    {
        List<HospitalEntity> hospitalList = this.hospitalRepository.findAll();
        //filter all appointment after today
        return this.hospitalMapper.toModelList(
                hospitalList
                        .stream()
                        .collect(Collectors.toList())
        );
    }


    @Override
    public Hospital add(Hospital hospital)
    {
        return this.hospitalMapper.toModel(this.hospitalRepository.save(this.hospitalMapper.toEntity(hospital)));
    }
    @Override
    public void deleteById(long id)
    {
        this.hospitalRepository.deleteById(id);
    }
}
