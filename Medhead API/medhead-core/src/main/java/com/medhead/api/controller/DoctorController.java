package com.medhead.api.controller;
import com.medhead.api.dto.Doctor;
import com.medhead.api.exception.AppointmentNotFoundException;
import com.medhead.api.exception.DoctorNotFoundException;
import com.medhead.api.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/doctor", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class DoctorController
{
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable long id)
    {
        try
        {
            return doctorService.findDoctorById(id);
        }
        catch (DoctorNotFoundException exc)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor Not Found", exc);
        }
    }

    @GetMapping
    public List<Doctor> getDoctors()
    {
        try
        {
            return doctorService.findAll();
        }
        catch (DoctorNotFoundException exc)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor Not Found", exc);
        }
    }
    @PostMapping
    public void add(Doctor doctor)
    {
        doctorService.add(doctor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id)
    {
        doctorService.removeById(id);
    }
}
