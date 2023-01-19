package com.medhead.api.controller;

import com.medhead.api.dao.entity.EmergencyEntity;
import com.medhead.api.dto.Appointment;
import com.medhead.api.dto.Emergency;
import com.medhead.api.services.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/emergency", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class EmergencyController
{
    @Autowired
    private EmergencyService emergencyService;

    @PostMapping("/add")
    public void add(@RequestBody Emergency emergency)
    {
        this.emergencyService.add(emergency);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id)
    {
        this.emergencyService.removeById(id);
    }
}
