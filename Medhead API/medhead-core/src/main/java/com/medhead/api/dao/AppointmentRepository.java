package com.medhead.api.dao;

import com.medhead.api.dao.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    public Appointment findAppointmentById(long id);

    public ArrayList<Appointment> findAppointmentByDoctorId(long doctorId);

    public ArrayList<Appointment> findAppointmentByDate(Date date);

    public void addById(long id);

    public void removeById(long id);
}
