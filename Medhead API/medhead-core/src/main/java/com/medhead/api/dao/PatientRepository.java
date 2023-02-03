package com.medhead.api.dao;

import com.medhead.api.dao.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    public PatientEntity findPatientById(long id);
    public void deleteById(long ids);
}
