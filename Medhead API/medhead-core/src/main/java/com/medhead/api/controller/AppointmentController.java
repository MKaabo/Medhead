package com.medhead.api.controller;

import com.medhead.api.dto.Appointment;
import com.medhead.api.exception.AppointmentNotFoundException;
import com.medhead.api.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Appointment add(@RequestBody Appointment appointment)
    {
        return this.appointmentService.add(appointment);
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable long id)
    {
        try
        {
            return appointmentService.findAppointmentById(id);
        }
        catch (AppointmentNotFoundException exc)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found", exc);
        }
    }

    @GetMapping
    public List<Appointment> getAppointmentByDoctorId(@RequestParam long doctorId)
    {
        try
        {
            return appointmentService.findAppointmentByDoctorId(doctorId);
        }
        catch (AppointmentNotFoundException exc)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found", exc);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id)
    {
        appointmentService.removeById(id);
    }

}
