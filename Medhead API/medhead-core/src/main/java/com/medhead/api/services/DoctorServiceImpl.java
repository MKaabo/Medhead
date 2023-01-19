package com.medhead.api.services;

import com.medhead.api.dao.DoctorRepository;
import com.medhead.api.dao.entity.DoctorEntity;
import com.medhead.api.dto.Doctor;
import com.medhead.api.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class DoctorServiceImpl implements DoctorService
{
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    public Doctor findDoctorById(long id)
    {
        return this.doctorMapper.toModel(this.doctorRepository.findById(id));
    }

    @Override
    public void add(Doctor doctor)
    {
        this.doctorRepository.save(this.doctorMapper.toEntity(doctor));
    }

    @Override
    public void removeById(long id) {
        this.doctorRepository.deleteById(id);
    }

}
