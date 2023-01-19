package com.medhead.api.dao;

import com.medhead.api.dao.entity.EmergencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmergencyRepository extends JpaRepository<EmergencyEntity, Long> {

    public List<EmergencyEntity> findAll();

    public EmergencyEntity findEmergencyByID(long id);

    public EmergencyEntity deleteById(long id);
}
