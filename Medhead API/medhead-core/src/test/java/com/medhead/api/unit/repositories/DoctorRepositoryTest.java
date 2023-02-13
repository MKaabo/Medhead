package com.medhead.api.unit.repositories;

import com.medhead.api.dao.DoctorRepository;
import com.medhead.api.dao.entity.DoctorEntity;
import com.medhead.api.dao.entity.HospitalEntity;
import com.medhead.api.dto.Doctor;
import com.medhead.api.dto.Hospital;
import com.medhead.api.mapper.DoctorMapper;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorRepositoryTest
{
    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private DoctorRepository doctorRepository;

    private DoctorEntity doctor;
    private Hospital hospitalDto;

    @BeforeEach
    public void createDoctor()
    {
        hospitalDto = new Hospital("Hôpital Vaugirard", "2.280501199302833,48.86794770440761", 600, 286);
        hospitalDto.setId(1);
        Doctor doctorDto = new Doctor("John B.", hospitalDto);
        this.doctor = doctorMapper.toEntity(doctorDto);
        this.doctor.setId(1);
    }

    @Test
    void contextLoads()
    {
        assertThat(this.doctorRepository).isNotNull();
    }

    @Test
    public void testFindById_ResourceNotFound()
    {
        assertThat(this.doctorRepository.findById(-1)).isNull();
        assertThat(this.doctorRepository.findById(99999999)).isNull();
    }
}
