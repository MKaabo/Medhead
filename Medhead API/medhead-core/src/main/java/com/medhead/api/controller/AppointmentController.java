package com.medhead.api.controller;

import com.medhead.api.dto.Appointment;
import com.medhead.api.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value= "/appointment", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class AppointmentController
{
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment add(@RequestBody Appointment appointment) {
        return this.appointmentService.add(appointment);
    }

    @GetMapping("/findById/{id}")
    public Appointment getAppointmentById(@PathVariable long id) {
        return appointmentService.findAppointmentById(id);
    }

    @GetMapping("/findByDoctorId/{doctorId}")
    public List<Appointment> getAppointmentByDoctorID(@PathVariable long doctorId) {
        return appointmentService.findAppointmentByDoctorId(doctorId);
    }

    @GetMapping("/findByDate/{date}")
    public List<Appointment> getPatientByDate(@PathVariable Date date) {
        return appointmentService.findAppointmentByDate(date);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable long id)
    {
        appointmentService.removeById(id);
    }

}
