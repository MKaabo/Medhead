package com.medhead.api.unit;
import com.medhead.api.dao.DoctorRepository;
import com.medhead.api.dao.entity.DoctorEntity;
import com.medhead.api.dto.Doctor;
import com.medhead.api.dto.Hospital;
import com.medhead.api.dto.Specialization;
import com.medhead.api.mapper.DoctorMapper;
import com.medhead.api.services.DoctorServiceImpl;
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
public class DoctorServiceTest
{
    @Autowired
    private DoctorServiceImpl doctorService;
    @Autowired
    private DoctorMapper doctorMapper;
    private Doctor doctor;
    @MockBean
    private DoctorRepository mockDoctorRepository;
    @BeforeEach
    public void createDoctor()
    {
        Hospital hospital = new Hospital("The Hospital", "55;-28", 800, 789);
        doctor = new Doctor("Valérie Trumy", hospital);
        doctor.setId(1);
        doctor.setSpecialization(Specialization.NEUROPATHOLOGY);
    }

    @Test
    void contextLoads()
    {
        assertThat(doctorService).isNotNull();
    }

    @Test
    public void testFindById_validateDoctor()
    {
        when(mockDoctorRepository.findById(anyLong()))
                .thenReturn(doctorMapper.toEntity(doctor));
        Doctor doctorTest = doctorService.findDoctorById(1);
        assertThat(doctorTest).usingRecursiveComparison().isEqualTo(doctor);
    }

    @Test
    public void testFindById_validateDifferentDoctor()
    {
        when(mockDoctorRepository.findById(anyLong()))
                .thenReturn(doctorMapper.toEntity(doctor));

        Doctor doctorTest = doctorService.findDoctorById(1);
        doctor.setSpecialization(Specialization.IMMUNOLOGY);
        assertThat(doctorTest).usingRecursiveComparison().isNotEqualTo(doctor);
    }
    @Test
    public void testFindAll_WithTwoDoctors()
    {
        Hospital hospital = new Hospital("The Other Hospital", "-138;170", 150, 140);
        Doctor doctor2 = new Doctor("Nathalie Cojida", hospital);
        doctor2.setSpecialization(Specialization.CARDIOLOGY);
        doctor2.setId(2);

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        doctors.add(doctor2);

        when(mockDoctorRepository.findAll()).thenReturn(doctorMapper.toEntityList(doctors));
        List<Doctor> doctorsReturned = doctorService.findAll();
        assertThat(doctorsReturned).usingRecursiveComparison().isEqualTo(doctors);
    }

    @Test
    public void testFindAll_Empty()
    {
        List<Doctor> doctors = new ArrayList<>();
        when(mockDoctorRepository.findAll()).thenReturn(doctorMapper.toEntityList(doctors));
        assertThat(doctorService.findAll().size()).isEqualTo(0);
    }
    @Test
    public void testAdd_validateAddCorrectDoctor()
    {
        when(mockDoctorRepository.save(Mockito.any(DoctorEntity.class)))
                .thenReturn(doctorMapper.toEntity(doctor));

        assertThat(doctorService.add(doctor)).usingRecursiveComparison().isEqualTo(doctor);
    }

}
