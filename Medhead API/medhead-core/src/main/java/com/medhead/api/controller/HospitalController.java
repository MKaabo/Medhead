package com.medhead.api.controller;

import com.medhead.api.dto.Doctor;
import com.medhead.api.dto.Hospital;
import com.medhead.api.services.DoctorService;
import com.medhead.api.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/hospital", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public void add(@RequestBody Hospital hospital) { this.hospitalService.add(hospital); }

    @GetMapping("/{id}")
    public Hospital getHospitalById(@PathVariable long id)
    {
        Hospital hospital = this.hospitalService.findHospitalById(id);
        List <Doctor> doctors = this.doctorService.findByHospitalId(id);
        hospital.setDoctors(doctors);
        return hospital;
    }

    @GetMapping
    public List<Hospital> getHospitals()
    {
        return this.hospitalService.findAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
       this.hospitalService.deleteById(id);
    }
}
