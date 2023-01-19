package com.medhead.api.controller;

import com.medhead.api.dao.entity.DoctorEntity;
import com.medhead.api.dto.Doctor;
import com.medhead.api.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/doctor", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class DoctorController
{
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/findById/{id}")
    public Doctor getDoctorById(@PathVariable long id) {
        return doctorService.findDoctorById(id);
    }

    @PostMapping("/add")
    public void add(Doctor doctor)
    {
        doctorService.add(doctor);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id)
    {
        doctorService.removeById(id);
    }
}
