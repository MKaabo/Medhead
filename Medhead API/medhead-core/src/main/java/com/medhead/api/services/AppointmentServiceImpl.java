package com.medhead.api.services;

import com.medhead.api.dao.AppointmentRepository;
import com.medhead.api.dao.entity.Appointment;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Component
@Transactional
public class AppointmentServiceImpl implements AppointmentService
{
    @Autowired
    public AppointmentRepository appointmentRepository;

    @Override
    public Appointment findAppointmentById(long id)
    {
        return findAppointmentById(id);
    }

    @Override
    public Appointment findAppointmentByDoctorId(long doctorId)
    {
        return findAppointmentByDoctorId(doctorId);
    }

    @Override
    public Appointment findAppointmentByDate(Date date)
    {
        return findAppointmentByDate(date);
    }

    @Override
    public void addById(long id) {
        appointmentRepository.addById(id);
    }

    @Override
    public void removeById(long id) {
        appointmentRepository.removeById(id);
    }
}
