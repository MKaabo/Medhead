package com.medhead.api.services;

import com.medhead.api.dao.EmergencyRepository;
import com.medhead.api.dto.Emergency;
import com.medhead.api.mapper.EmergencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmergencyServiceImpl implements EmergencyService
{
    @Autowired
    private EmergencyRepository emergencyRepository;
    @Autowired
    private EmergencyMapper emergencyMapper;
    @Override
    public Emergency findEmergencyById(long id)
    {
        return this.emergencyMapper.toModel(emergencyRepository.findEmergencyById(id));
    }

    @Override
    public Emergency add(Emergency emergency) {
        return this.emergencyMapper.toModel(this.emergencyRepository.save(this.emergencyMapper.toEntity(emergency)));
    }

    @Override
    public void removeById(long id) {
        this.emergencyRepository.deleteById(id);
    }

}
