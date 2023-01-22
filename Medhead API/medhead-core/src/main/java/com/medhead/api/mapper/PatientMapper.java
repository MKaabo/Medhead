package com.medhead.api.mapper;

import com.medhead.api.dao.entity.PatientEntity;
import com.medhead.api.dto.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { })
public interface PatientMapper extends GenericMapper<PatientEntity, Patient> {
}
