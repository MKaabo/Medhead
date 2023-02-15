package com.medhead.api.unit.repositories;

import com.medhead.api.dao.PatientRepository;
import com.medhead.api.dao.entity.PatientEntity;
import com.medhead.api.dto.Patient;
import com.medhead.api.mapper.PatientMapper;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientRepositoryTest
{
    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private PatientRepository patientRepository;

    private PatientEntity patient;

    @BeforeEach
    public void createPatient()
    {
        Patient patientDto = new Patient("Jean Cassel", 58, "10.2,147.5");
        this.patient = patientMapper.toEntity(patientDto);
        this.patient.setId(1);
    }

    @Test
    void contextLoads()
    {
        assertThat(this.patientRepository).isNotNull();
    }

    @Test
    public void testFindById_ResourceNotFound()
    {
        assertThat(this.patientRepository.findPatientById(-1)).isNull();
        assertThat(this.patientRepository.findPatientById(99999999)).isNull();
    }

    @Nested
    @Tag("SaveIncorrectValues")
    @DisplayName("Try to save incorrect patient")
    class SaveIncorrectPatientTest
    {
        @Test
        public void testSave_incorrectCoordinatesPatient()
        {
            boolean exceptionThrown;
            try
            {
                patient.setPosition("1567894");
                patientRepository.save(patient);
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_incorrectNameEmptyPatient()
        {
            boolean exceptionThrown;
            try
            {
                patient.setName("");
                assertThat(patientRepository.save(patient)).isNull();
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_incorrectPhoneNumberPatient()
        {
            boolean exceptionThrown;
            try
            {
                patient.setPhone("04425349''!");
                assertThat(patientRepository.save(patient)).isNull();
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_incorrectAgePatient()
        {
            Boolean exceptionThrown;
            try
            {
                patient.setAge(158);
                patientRepository.save(patient);
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();

            exceptionThrown = null;
            try
            {
                patient.setAge(-3);
                patientRepository.save(patient);
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_incorrectEmailPatient()
        {
            boolean exceptionThrown;
            try
            {
                patient.setEmail("user@gmailcom");
                assertThat(patientRepository.save(patient)).isNull();
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }

    }
}
