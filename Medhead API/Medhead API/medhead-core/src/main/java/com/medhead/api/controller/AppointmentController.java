package com.medhead.api.controller;

import com.medhead.api.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/appointment")
public class AppointmentController
{
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/findAll")
    public ArrayList<Appointment> getAllAppointments() {
        return appointmentService.findAllAppointments();
    }

    @GetMapping("/findById/{id}")
    public Appointment getAppointmentByID(@PathVariable long id) {
        return appointmentService.findAppointmentByID(id);
    }

    @GetMapping("/findByDoctorId/{id}")
    public Appointment getAppointmentByDoctorID(@PathVariable long doctorID) {
        return appointmentService.findAppointmentByDoctorID(doctorID);
    }

    @GetMapping("/findByDate/{date}")
    public Appointment getPatientByDate(@PathVariable Date date) {
        return appointmentService.findAppointmentByDate(date);
    }

    @PostMapping("/add")
    public void add(Appointment appointment)
    {
        appointmentService.addAppointment(appointment);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id)
    {
        appointmentService.removeAppointment(id);
    }

}
