package com.medhead.api.controller;

import com.medhead.api.entity.Emergency;
import com.medhead.api.services.EmergencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emergency")
public class EmergencyController
{
    @Autowired
    EmergencyServiceImpl emergencyServiceImpl;

    @PostMapping("/add")
    public void add(Emergency emergency)
    {
        emergencyServiceImpl.addEmergency(emergency);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable long id)
    {
        emergencyServiceImpl.removeEmergency(id);
    }
}
