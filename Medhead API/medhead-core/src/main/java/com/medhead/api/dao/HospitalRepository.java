package com.medhead.api.dao;

import com.medhead.api.dao.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Long>
{
    public HospitalEntity findHospitalById(long id);
    public void deleteById(long id);
}
