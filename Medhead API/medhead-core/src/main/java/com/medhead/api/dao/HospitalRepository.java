package com.medhead.api.dao;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.dao.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {
    public HospitalEntity findHospitalById(long id);

    public HospitalEntity findHospitalByPosition(String pos);
    public void deleteById(long id);
}
