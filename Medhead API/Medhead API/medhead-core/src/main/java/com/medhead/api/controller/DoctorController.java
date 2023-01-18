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
    private DoctorService doctorService;

    @GetMapping("/findById/{id}")
    public Doctor getDoctorById(@PathVariable long id) {
        return doctorService.findDoctorById(id);
    }

    @PostMapping("/add")
    public void add(Doctor doctor)
    {
        doctorService.addDoctor(doctor);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id)
    {
        doctorService.removeDoctor(id);
    }
}
