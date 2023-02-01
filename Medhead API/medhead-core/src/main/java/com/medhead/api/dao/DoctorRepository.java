package com.medhead.api.dao;

import com.medhead.api.dao.entity.DoctorEntity;
import com.medhead.api.dto.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    public DoctorEntity findById(long id);

    public ArrayList<DoctorEntity> findAll();

    public List<DoctorEntity> findByHospitalId(long hospitalId);
    public void deleteById(long id);
}
