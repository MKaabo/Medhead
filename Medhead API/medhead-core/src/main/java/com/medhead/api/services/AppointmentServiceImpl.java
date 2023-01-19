package com.medhead.api.services;

import com.medhead.api.dao.AppointmentRepository;
import com.medhead.api.dao.entity.AppointmentEntity;
import com.medhead.api.dto.Appointment;
import com.medhead.api.mapper.AppointmentMapper;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@Service
public class AppointmentServiceImpl implements AppointmentService
{
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    public Appointment add(Appointment appointment){
        return this.appointmentMapper.toModel(this.appointmentRepository.save(this.appointmentMapper.toEntity(appointment)));
    }

    @Override
    public Appointment findAppointmentById(long id)
    {
        return this.appointmentMapper.toModel(this.appointmentRepository.findById(id));
    }

    @Override
    public List<Appointment> findAppointmentByDoctorId(long doctorId)
    {
        List<AppointmentEntity> appointmentList = this.appointmentRepository.findByDoctor_Id(doctorId);
        //filter all appointment after today
        return this.appointmentMapper.toModelList(
            appointmentList
                .stream()
                .filter(appointment -> appointment.getDate().after(new Date()))
                .collect(Collectors.toList())
        );
    }

    @Override
    public List<Appointment> findAppointmentByDate(Date date)
    {
        return this.appointmentMapper.toModelList(this.appointmentRepository.findByDate(date));
    }

    @Override
    public void removeById(long id) {
        this.appointmentRepository.deleteById(id);
    }
}
