package com.medhead.api.dao;

import com.medhead.api.dao.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long>
{
    public DoctorEntity findById(long id);
    public List<DoctorEntity> findByHospitalId(long hospitalId);
    public void deleteById(long id);
}
