package com.medhead.api.services;

import com.medhead.api.dao.PatientRepository;
import com.medhead.api.dao.entity.PatientEntity;
import com.medhead.api.dto.Patient;
import com.medhead.api.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService
{
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientMapper patientMapper;
    @Override
    public Patient findPatientById(long id) {
        return this.patientMapper.toModel(patientRepository.findPatientById(id));
    }

    @Override
    public List<Patient> findAll() {
        List<PatientEntity> hospitalList = this.patientRepository.findAll();
        return this.patientMapper.toModelList(
                hospitalList
                        .stream()
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Patient add(Patient patient) {
        return this.patientMapper.toModel(this.patientRepository.save(this.patientMapper.toEntity(patient)));
    }

    @Override
    public Patient updatePatient(long id, Patient patient) {
        patient.setId(id);
        return this.patientMapper.toModel(
                this.patientRepository.save(this.patientMapper.toEntity(patient)));
    }

    @Override
    public void deleteById(long id)
    {
        this.patientRepository.deleteById(id);
    }
}
