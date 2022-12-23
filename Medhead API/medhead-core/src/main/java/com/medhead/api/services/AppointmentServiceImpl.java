package com.medhead.api.services;

import com.medhead.api.dao.AppointmentRepository;
import com.medhead.api.entity.Appointment;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService
{
    @Autowired
    public AppointmentRepository appointmentRepository;

    @Override
    public ArrayList<Appointment> findAllAppointments()
    {
        return appointmentRepository.findAllAppointments();
    }

    @Override
    public Appointment findAppointmentByID(long id)
    {
        return findAppointmentByID(id);
    }

    @Override
    public Appointment findAppointmentByDoctorID(long doctorID)
    {
        return findAppointmentByDoctorID(doctorID);
    }

    @Override
    public Appointment findAppointmentByDate(Date date)
    {
        return findAppointmentByDate(date);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentRepository.addAppointment(appointment);
    }

    @Override
    public void removeAppointment(long id) {
        appointmentRepository.removeAppointment(id);
    }
}
