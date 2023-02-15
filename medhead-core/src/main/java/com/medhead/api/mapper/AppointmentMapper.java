package com.medhead.api.mapper;

import com.medhead.api.dao.entity.AppointmentEntity;
import com.medhead.api.dto.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {HospitalMapper.class, PatientMapper.class})
public interface AppointmentMapper extends GenericMapper<AppointmentEntity, Appointment> {
}
