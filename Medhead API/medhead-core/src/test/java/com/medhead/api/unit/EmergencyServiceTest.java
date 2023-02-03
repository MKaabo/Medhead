package com.medhead.api.unit;
import com.medhead.api.dao.DoctorRepository;
import com.medhead.api.dao.EmergencyRepository;
import com.medhead.api.dao.entity.EmergencyEntity;
import com.medhead.api.dto.Emergency;
import com.medhead.api.dto.Hospital;
import com.medhead.api.dto.Patient;
import com.medhead.api.mapper.EmergencyMapper;
import com.medhead.api.services.EmergencyServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmergencyServiceTest
{
    @Autowired
    private EmergencyServiceImpl emergencyService;
    @Autowired
    private EmergencyMapper emergencyMapper;
    private Emergency emergency;
    @MockBean
    private EmergencyRepository mockEmergencyRepository;
    @MockBean
    private RestTemplate mockMapboxRestTemplate;
    @MockBean
    private DoctorRepository mockDoctorRepository;

    @BeforeEach
    public void createEmergency()
    {
        Hospital hospital = new Hospital("The Hospital", "55;-28", 800, 789);
        hospital.setId(1);
        Patient patient = new Patient("Jean Cassel", 58, "10.2;147");
        patient.setId(1);

        this.emergency = new Emergency(patient);
        this.emergency.setId(1);
    }

    @Test
    void contextLoads()
    {
        assertThat(this.emergencyService).isNotNull();
    }

    @Test
    public void testFindById_validateEmergency()
    {
        when(this.mockEmergencyRepository.findEmergencyById(anyLong()))
                .thenReturn(this.emergencyMapper.toEntity(this.emergency));
        Emergency emergencyTest = this.emergencyService.findEmergencyById(1);
        assertThat(emergencyTest).usingRecursiveComparison().isEqualTo(this.emergency);
    }

    @Test
    public void testFindById_validateDifferentEmergency()
    {
        when(this.mockEmergencyRepository.findEmergencyById(anyLong()))
                .thenReturn(this.emergencyMapper.toEntity(this.emergency));
        Emergency emergencyTest = emergencyService.findEmergencyById(1);
        this.emergency.setId(2);
        assertThat(emergencyTest).usingRecursiveComparison().isNotEqualTo(this.emergency);
    }
    @Test
    public void testFindAll_WithTwoEmergencies()
    {
        Hospital hospital = new Hospital("The Other Hospital", "-138;170", 150, 140);
        Patient patient = new Patient("Jay Quallin", 71, "-137;170");
        Emergency emergency2 = new Emergency(patient);
        emergency2.setHospital(hospital);
        emergency2.setId(2);

        List<Emergency> emergencies = new ArrayList<>();
        emergencies.add(this.emergency);
        emergencies.add(emergency2);

        when(this.mockEmergencyRepository.findAll()).thenReturn(this.emergencyMapper.toEntityList(emergencies));
        List<Emergency> emergenciesReturned = this.emergencyService.findAll();
        assertThat(emergenciesReturned).usingRecursiveComparison().isEqualTo(emergencies);
    }

    @Test
    public void testFindAll_Empty()
    {
        List<Emergency> emergencies = new ArrayList<>();
        when(this.mockEmergencyRepository.findAll()).thenReturn(this.emergencyMapper.toEntityList(emergencies));
        assertThat(this.emergencyService.findAll().size()).isEqualTo(0);
    }

    @Nested
    @Tag("AddEmergenciesAndFindHospital")
    @DisplayName("Test hospital finding algorithm")
    class TestHospitalFindingAlgorithm
    {
        @Test
        public void testAdd_validateAddCorrectEmergency()
        {
            when(mockEmergencyRepository.save(Mockito.any(EmergencyEntity.class)))
                    .thenReturn(emergencyMapper.toEntity(emergency));

     //       assertThat(emergencyService.add(emergency)).usingRecursiveComparison().isEqualTo(emergency);
        }
    }
}
