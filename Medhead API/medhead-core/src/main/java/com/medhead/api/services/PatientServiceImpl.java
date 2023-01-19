package com.medhead.api.services;

import com.medhead.api.dao.PatientRepository;
import com.medhead.api.dto.Patient;
import com.medhead.api.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService
{
    @Autowired
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;
    @Override
    public Patient findPatientById(long id) {
        return this.patientMapper.toModel(patientRepository.findPatientById(id));
    }

    @Override
    public Patient add(Patient patient) {
        return this.patientMapper.toModel(this.patientRepository.save(this.patientMapper.toEntity(patient)));
    }
    @Override
    public void deleteById(long id)
    {
        this.patientRepository.deleteById(id);
    }
}
