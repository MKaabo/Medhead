package com.medhead.api.controller;

import com.medhead.api.entity.Doctor;
import com.medhead.api.services.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController
{
    @Autowired
    public DoctorServiceImpl doctorServiceImpl;

    @GetMapping("/findById/{id}")
    public Doctor getDoctorById(@PathVariable long id) {
        return doctorServiceImpl.findDoctorById(id);
    }

    @PostMapping("/add")
    public void add(Doctor doctor)
    {
        doctorServiceImpl.addDoctor(doctor);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id)
    {
        doctorServiceImpl.removeDoctor(id);
    }
}
