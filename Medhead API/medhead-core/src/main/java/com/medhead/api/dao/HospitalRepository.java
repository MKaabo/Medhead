package com.medhead.api.dao;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.dao.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {
    public HospitalEntity findHospitalByID(long id);

    public HospitalEntity findHospitalByProximity(Point coord);
    public void deleteById(long id);
}
