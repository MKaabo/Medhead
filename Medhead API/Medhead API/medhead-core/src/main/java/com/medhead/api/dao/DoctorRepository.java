package com.medhead.api.dao;

import com.medhead.api.entity.Doctor;
import com.medhead.api.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    public Date getDispoByID(long id, Date date);

    public ArrayList<Doctor> findAllDoctor();

    public Doctor findDoctorByID(long id);

    public Doctor findDoctorsBySpecialization(Specialization specialization);

    public void addDoctor(Doctor doctor);

    public void removeDoctor(long id);
}