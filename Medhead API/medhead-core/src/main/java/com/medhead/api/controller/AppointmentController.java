package com.medhead.api.controller;

import com.medhead.api.dao.entity.Appointment;
import com.medhead.api.services.AppointmentService;
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

    @GetMapping("/findById/{id}")
    public Appointment getAppointmentById(@PathVariable long id) {
        return appointmentService.findAppointmentById(id);
    }

    @GetMapping("/findByDoctorId/{doctorId}")
    public Appointment getAppointmentByDoctorID(@PathVariable long doctorId) {
        return appointmentService.findAppointmentByDoctorId(doctorId);
    }

    @GetMapping("/findByDate/{date}")
    public Appointment getPatientByDate(@PathVariable Date date) {
        return appointmentService.findAppointmentByDate(date);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable long id)
    {
        appointmentService.removeById(id);
    }

}
