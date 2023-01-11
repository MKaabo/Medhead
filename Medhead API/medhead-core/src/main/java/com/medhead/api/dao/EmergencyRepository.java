package com.medhead.api.dao;

import com.medhead.api.dao.entity.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmergencyRepository extends JpaRepository<Emergency, Long> {

    public ArrayList<Emergency> findAllEmergency();

    public Emergency findEmergencyByID(long id);

    public void removeEmergency(long id);

    public default void addEmergency(Emergency emergency) {

    }
}
