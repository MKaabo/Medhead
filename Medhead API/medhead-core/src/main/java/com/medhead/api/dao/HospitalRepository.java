package com.medhead.api.dao;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.dao.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    public ArrayList <Hospital> findAllHospital();

    public Hospital findHospitalByID(long id);

    public Hospital findHospitalByProximity(Point coord);

    public Hospital findHospitalByArea(Point coord, long range);

    public void addHospital(Hospital hospital);

    public void removeHospital(long id);
}
