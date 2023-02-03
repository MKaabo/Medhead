package com.medhead.api.unit;
import com.medhead.api.dao.PatientRepository;
import com.medhead.api.dao.entity.PatientEntity;
import com.medhead.api.dto.Patient;
import com.medhead.api.dto.Specialization;
import com.medhead.api.mapper.PatientMapper;
import com.medhead.api.services.PatientServiceImpl;
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
public class PatientServiceTest
{
    @Autowired
    private PatientServiceImpl patientService;
    @Autowired
    private PatientMapper patientMapper;
    private Patient patient;
    @MockBean
    private PatientRepository mockPatientRepository;
    @BeforeEach
    public void createPatient()
    {
        patient = new Patient("Jean Cassel", 58, "10.2;147");
        patient.setId(1);
    }

    @Test
    void contextLoads()
    {
        assertThat(patientService).isNotNull();
    }

    @Test
    public void testFindById_validatePatient()
    {
        when(mockPatientRepository.findPatientById(anyLong()))
                .thenReturn(patientMapper.toEntity(patient));
        Patient patientTest = patientService.findPatientById(1);
        assertThat(patientTest).usingRecursiveComparison().isEqualTo(patient);
    }

    @Test
    public void testFindById_validateDifferentPatient()
    {
        when(mockPatientRepository.findPatientById(anyLong()))
                .thenReturn(patientMapper.toEntity(patient));
        Patient patientTest = patientService.findPatientById(1);
        patient.setPosition("1;-2");
        assertThat(patientTest).usingRecursiveComparison().isNotEqualTo(patient);
    }
    @Test
    public void testFindAll_WithTwoPatients()
    {
        Patient patient2 = new Patient("Paul Ruff", 70, "11.58;-1,9");
        patient2.setSpecialization(Specialization.NEUROPATHOLOGY);
        patient2.setId(2);

        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        patients.add(patient2);

        when(mockPatientRepository.findAll()).thenReturn(patientMapper.toEntityList(patients));
        List<Patient> patientsReturned = patientService.findAll();
        assertThat(patientsReturned).usingRecursiveComparison().isEqualTo(patients);
    }

    @Test
    public void testFindAll_Empty()
    {
        List<Patient> patients = new ArrayList<>();
        when(mockPatientRepository.findAll()).thenReturn(patientMapper.toEntityList(patients));
        assertThat(patientService.findAll().size()).isEqualTo(0);
    }
    @Test
    public void testAdd_validateAddCorrectPatient()
    {
        when(mockPatientRepository.save(Mockito.any(PatientEntity.class)))
                .thenReturn(patientMapper.toEntity(patient));

        assertThat(patientService.add(patient)).usingRecursiveComparison().isEqualTo(patient);
    }

}
