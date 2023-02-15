package com.medhead.api.mapper;

import com.medhead.api.dao.entity.HospitalEntity;
import com.medhead.api.dto.Hospital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class})
public interface HospitalMapper extends GenericMapper<HospitalEntity, Hospital> {
}
