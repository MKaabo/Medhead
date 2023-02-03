package com.medhead.api.unit;
import com.medhead.api.dao.HospitalRepository;
import com.medhead.api.dao.entity.HospitalEntity;
import com.medhead.api.dto.Hospital;
import com.medhead.api.mapper.HospitalMapper;
import com.medhead.api.services.HospitalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalServiceTest
{
    @Autowired
    private HospitalServiceImpl hospitalService;
    @Autowired
    private HospitalMapper hospitalMapper;
    private Hospital hospital;
    @MockBean
    private HospitalRepository mockHospitalRepository;
    @BeforeEach
    public void createHospital()
    {
        hospital = new Hospital("Hopital", "4.58;125,9", 10, 10);
        hospital.setId(1);
    }

    @Test
    void contextLoads()
    {
        assertThat(hospitalService).isNotNull();
    }

    @Test
    public void testFindById_validateHospital()
    {
        when(mockHospitalRepository.findHospitalById(anyLong()))
                .thenReturn(hospitalMapper.toEntity(hospital));
        Hospital hospitalTest = hospitalService.findHospitalById(1);
        assertThat(hospitalTest).usingRecursiveComparison().isEqualTo(hospital);
    }

    @Test
    public void testFindById_validateDifferentHospital()
    {
        when(mockHospitalRepository.findHospitalById(anyLong()))
                .thenReturn(hospitalMapper.toEntity(hospital));
        Hospital hospitalTest = hospitalService.findHospitalById(1);
        hospital.setPosition("1;-2");
        assertThat(hospitalTest).usingRecursiveComparison().isNotEqualTo(hospital);
    }
    @Test
    public void testFindAll_WithTwoHospitals()
    {
        Hospital hospital2 = new Hospital("Hopital2", "9.58;175,9", 20, 20);
        hospital2.setId(2);
        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(hospital);
        hospitals.add(hospital2);

        when(mockHospitalRepository.findAll()).thenReturn(hospitalMapper.toEntityList(hospitals));
        assertThat(hospitalService.findAll()).containsAll(hospitals);
    }

    @Test
    public void testFindAll_Empty()
    {
        List<Hospital> hospitals = new ArrayList<>();
        when(mockHospitalRepository.findAll()).thenReturn(hospitalMapper.toEntityList(hospitals));
        assertThat(hospitalService.findAll().size()).isEqualTo(0);
    }
    @Test
    public void testAdd_validateAddCorrectHospital()
    {
        when(mockHospitalRepository.save(Mockito.any(HospitalEntity.class)))
                .thenReturn(hospitalMapper.toEntity(hospital));

        assertThat(hospitalService.add(hospital)).usingRecursiveComparison().isEqualTo(hospital);
    }

}
