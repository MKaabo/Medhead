package com.medhead.api.dao;

import com.medhead.api.dao.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    public AppointmentEntity findById(long id);

    public List<AppointmentEntity> findByDoctor_Id(long doctorId);

    public List<AppointmentEntity> findByDate(Date date);

    public void deleteById(long id);
}
