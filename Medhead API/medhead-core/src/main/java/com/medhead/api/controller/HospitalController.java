package com.medhead.api.controller;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.dao.entity.Hospital;
import com.medhead.api.services.HospService;
import com.medhead.api.services.HospServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    private HospService hospService;

    @PostMapping("/add")
    public void add(Hospital hospital) { hospService.addHospital(hospital); }

    @GetMapping("/findAll")
    public ArrayList<Hospital> getAllHospitals() {
        return hospService.findAllHospitals();
    }

    @GetMapping("/findById/{id}")
    public Hospital getHospitalByID(@PathVariable long id) {
        return hospService.findHospitalByID(id);
    }

    @GetMapping("/findByCoord/{coordinates}")
    public Hospital getClosestAvailableHospital(@PathVariable Point coordinates)
    {
        return hospService.getClosestAvailableHospital(coordinates);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id) {
        hospService.removeHospital(id);
    }
}
