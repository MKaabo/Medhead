package com.medhead.api.controller;

import com.medhead.api.entity.Appointment;
import com.medhead.api.services.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/appointment")
public class AppointmentController
{
    @Autowired
    AppointmentServiceImpl appointmentServiceImpl;

    @GetMapping("/findAll")
    public ArrayList<Appointment> getAllAppointments() {
        return appointmentServiceImpl.findAllAppointments();
    }

    @GetMapping("/findById/{id}")
    public Appointment getAppointmentByID(@PathVariable long id) {
        return appointmentServiceImpl.findAppointmentByID(id);
    }

    @GetMapping("/findByDoctorId/{id}")
    public Appointment getAppointmentByDoctorID(@PathVariable long doctorID) {
        return appointmentServiceImpl.findAppointmentByDoctorID(doctorID);
    }

    @GetMapping("/findByDate/{date}")
    public Appointment getPatientByDate(@PathVariable Date date) {
        return appointmentServiceImpl.findAppointmentByDate(date);
    }

    @PostMapping("/add")
    public void add(Appointment appointment)
    {
        appointmentServiceImpl.addAppointment(appointment);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id)
    {
        appointmentServiceImpl.removeAppointment(id);
    }

}
