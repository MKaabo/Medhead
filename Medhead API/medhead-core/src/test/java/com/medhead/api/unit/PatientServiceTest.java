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
        this.patient = new Patient("Jean Cassel", 58, "10.2;147");
        this.patient.setId(1);
    }

    @Test
    void contextLoads()
    {
        assertThat(this.patientService).isNotNull();
    }

    @Test
    public void testFindById_validatePatient()
    {
        when(this.mockPatientRepository.findPatientById(anyLong()))
                .thenReturn(this.patientMapper.toEntity(this.patient));
        Patient patientTest = this.patientService.findPatientById(1);
        assertThat(patientTest).usingRecursiveComparison().isEqualTo(this.patient);
    }

    @Test
    public void testFindById_validateDifferentPatient()
    {
        when(this.mockPatientRepository.findPatientById(anyLong()))
                .thenReturn(this.patientMapper.toEntity(this.patient));
        Patient patientTest = this.patientService.findPatientById(1);
        this.patient.setPosition("1;-2");
        assertThat(patientTest).usingRecursiveComparison().isNotEqualTo(this.patient);
    }
    @Test
    public void testFindAll_WithTwoPatients()
    {
        Patient patient2 = new Patient("Paul Ruff", 70, "11.58;-1,9");
        patient2.setSpecialization(Specialization.NEUROPATHOLOGY);
        patient2.setId(2);

        List<Patient> patients = new ArrayList<>();
        patients.add(this.patient);
        patients.add(patient2);

        when(this.mockPatientRepository.findAll()).thenReturn(this.patientMapper.toEntityList(patients));
        List<Patient> patientsReturned = this.patientService.findAll();
        assertThat(patientsReturned).usingRecursiveComparison().isEqualTo(patients);
    }

    @Test
    public void testFindAll_Empty()
    {
        List<Patient> patients = new ArrayList<>();
        when(this.mockPatientRepository.findAll()).thenReturn(this.patientMapper.toEntityList(patients));
        assertThat(this.patientService.findAll().size()).isEqualTo(0);
    }
    @Test
    public void testAdd_validateAddCorrectPatient()
    {
        when(this.mockPatientRepository.save(Mockito.any(PatientEntity.class)))
                .thenReturn(this.patientMapper.toEntity(this.patient));

        assertThat(this.patientService.add(this.patient)).usingRecursiveComparison().isEqualTo(this.patient);
    }

}
