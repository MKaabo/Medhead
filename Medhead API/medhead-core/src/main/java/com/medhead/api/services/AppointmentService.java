package com.medhead.api.services;
import com.medhead.api.entity.Appointment;

import java.util.ArrayList;
import java.util.Date;

public interface AppointmentService {

    void addAppointment(Appointment appointment);

    void removeAppointment(long id);

    ArrayList<Appointment> findAllAppointments();

    Appointment findAppointmentByID(long id);

    Appointment findAppointmentByDoctorID(long doctorID);

    Appointment findAppointmentByDate(Date date);
}
