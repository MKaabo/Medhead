package com.medhead.api.mapper;

import com.medhead.api.dao.entity.DoctorEntity;
import com.medhead.api.dto.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper extends GenericMapper<DoctorEntity, Doctor> {
}
