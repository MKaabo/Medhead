package com.medhead.api.services;
import com.medhead.api.dao.entity.EmergencyEntity;
import com.medhead.api.dto.Emergency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmergencyService
{
    public Emergency findEmergencyById(long id);

    public Emergency add(Emergency emergency);

    public void removeById(long id);
}
