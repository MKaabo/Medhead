package com.medhead.api.dao;

import com.medhead.api.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    public ArrayList<Appointment> findAllAppointments();

    public Appointment findAppointmentByID(long id);

    public ArrayList<Appointment> findAppointmentByDoctorID(long doctorID);

    public ArrayList<Appointment> findAppointmentByDate(Date date);

    public void addAppointment(Appointment appointment);

    public void removeAppointment(long id);
}