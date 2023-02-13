package com.medhead.api.unit.repositories;

import com.medhead.api.dao.EmergencyRepository;
import com.medhead.api.dao.entity.EmergencyEntity;
import com.medhead.api.dto.Emergency;
import com.medhead.api.dto.Patient;
import com.medhead.api.mapper.EmergencyMapper;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmergencyRepositoryTest
{
    @Autowired
    private EmergencyMapper emergencyMapper;

    @Autowired
    private EmergencyRepository emergencyRepository;

    private EmergencyEntity emergency;

    @BeforeEach
    public void createEmergency()
    {
        Patient patientDto = new Patient("Jean Cassel", 58, "10.2,147.5");
        Emergency emergencyDto = new Emergency(patientDto);
        this.emergency = emergencyMapper.toEntity(emergencyDto);
        this.emergency.setId(1);
    }

    @Test
    void contextLoads()
    {
        assertThat(this.emergencyRepository).isNotNull();
    }

    @Test
    public void testFindById_ResourceNotFound()
    {
        assertThat(this.emergencyRepository.findEmergencyById(-1)).isNull();
        assertThat(this.emergencyRepository.findEmergencyById(99999999)).isNull();
    }

    @Nested
    @Tag("SaveIncorrectValues")
    @DisplayName("Try to save incorrect emergency")
    class SaveIncorrectEmergencyTest
    {
        @Test
        public void testSave_nullPatientEmergency()
        {
            boolean exceptionThrown;
            try
            {
                emergency.setPatient(null);
                emergencyRepository.save(emergency);
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
