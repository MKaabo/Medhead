package com.medhead.api.services;
import com.medhead.api.dao.entity.Appointment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public interface AppointmentService {

    void addById(long id);

    void removeById(long id);

    Appointment findAppointmentById(long id);

    Appointment findAppointmentByDoctorId(long doctorId);

    Appointment findAppointmentByDate(Date date);
}
