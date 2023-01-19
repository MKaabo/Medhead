package com.medhead.api.controller;

import com.mapbox.services.commons.geojson.Point;
import com.medhead.api.dto.Hospital;
import com.medhead.api.services.HospService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/hospital", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class HospitalController {
    @Autowired
    private HospService hospService;

    @PostMapping("/add")
    public void add(@RequestBody Hospital hospital) { this.hospService.add(hospital); }
    @GetMapping("/findById/{id}")
    public Hospital getHospitalById(@PathVariable long id) {
        return this.hospService.findHospitalById(id);
    }

    @GetMapping("/findByPosition/{pos}")
    public Hospital getClosestAvailableHospital(@PathVariable String pos)
    {
        return this.hospService.findHospitalByPosition(pos);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id) {
       this.hospService.deleteById(id);
    }
}
