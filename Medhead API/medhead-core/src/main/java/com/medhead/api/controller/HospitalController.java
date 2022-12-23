package com.medhead.api.controller;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.entity.Hospital;
import com.medhead.api.services.HospServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    HospServiceImpl hospServiceImpl;

    @PostMapping("/add")
    public void add(Hospital hospital) { hospServiceImpl.addHospital(hospital); }

    @GetMapping("/findAll")
    public ArrayList<Hospital> getAllHospitals() {
        return hospServiceImpl.findAllHospitals();
    }

    @GetMapping("/findById/{id}")
    public Hospital getHospitalByID(@PathVariable long id) {
        return hospServiceImpl.findHospitalByID(id);
    }

    @GetMapping("/findByCoord/{coord}")
    public Hospital getClosestAvailableHospital(@PathVariable Point coordinates)
    {
        return hospServiceImpl.getClosestAvailableHospital(coordinates);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id) {
        hospServiceImpl.removeHospital(id);
    }
}
