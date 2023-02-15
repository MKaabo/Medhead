package com.medhead.api.mapper;

import com.medhead.api.dao.entity.EmergencyEntity;
import com.medhead.api.dto.Emergency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {HospitalMapper.class, PatientMapper.class})
public interface EmergencyMapper extends GenericMapper<EmergencyEntity, Emergency> {
}
