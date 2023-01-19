package com.medhead.api.services;
import com.medhead.api.dto.Appointment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface AppointmentService {

    Appointment findAppointmentById(long id);

    List<Appointment> findAppointmentByDoctorId(long doctorId);

    List<Appointment> findAppointmentByDate(Date date);

    void removeById(long id);

    Appointment add(Appointment appointment);
}
